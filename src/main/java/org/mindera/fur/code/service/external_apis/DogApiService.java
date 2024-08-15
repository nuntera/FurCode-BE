package org.mindera.fur.code.service.external_apis;

import jakarta.validation.Valid;
import org.mindera.fur.code.dto.external_apis.dog_api.DogBreedDTO;
import org.mindera.fur.code.dto.external_apis.dog_api.DogBreedsNamesDTO;
import org.mindera.fur.code.mapper.external_apis.DogBreedMapper;
import org.mindera.fur.code.messages.pet.PetMessages;
import org.mindera.fur.code.model.external_apis.dog_api.DogBreedByIdResponse;
import org.mindera.fur.code.model.external_apis.dog_api.DogBreedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * Dog API service class for making requests to the Dog API.
 */
@Validated
@Service
public class DogApiService {
    private static final String BREEDS_URL = "/breeds";
    private final RestTemplate restTemplate;

    @Value("${dog_api.base.url}")
    private String apiBaseUrl;

    @Autowired
    public DogApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Fetches a dog breed by its ID.
     *
     * @param id the ID of the dog breed and description
     * @return the dog breed
     */
    @Cacheable(value = "dogBreeds", key = "#id")
    public DogBreedDTO fetchBreedById(@Valid String id) {
        String url = buildUrlWithId(id);
        DogBreedByIdResponse response = executeGetRequest(url, DogBreedByIdResponse.class);
        return mapToBreedDTO(response, id);
    }

    /**
     * Fetches all dog breed names.
     *
     * @return the list of dog breed names
     */
    @Cacheable(value = "allBreedsNames")
    public DogBreedsNamesDTO fetchAllBreedsNames() {
        List<String> allBreedsNames = new ArrayList<>();
        String url = apiBaseUrl + BREEDS_URL;
        boolean hasNextPage = true;

        while (hasNextPage) {
            DogBreedResponse response = executeGetRequest(url, DogBreedResponse.class);
            addBreedNamesToList(response, allBreedsNames);
            url = getNextPageUrl(response);
            hasNextPage = (url != null);
        }
        return new DogBreedsNamesDTO(allBreedsNames);
    }

    /**
     * Fetches a dog breed by its name.
     *
     * @param breedName the name of the dog breed
     * @return the dog breed
     */
    @Cacheable(value = "dogBreedCache", key = "#breedName")
    public DogBreedDTO getBreedByName(@Valid String breedName) {
        String url = apiBaseUrl + BREEDS_URL;
        boolean hasNextPage = true;

        while (hasNextPage) {
            DogBreedResponse response = executeGetRequest(url, DogBreedResponse.class);
            DogBreedDTO foundBreed = findBreedByName(response, breedName);
            if (foundBreed != null) {
                return foundBreed;
            }
            url = getNextPageUrl(response);
            hasNextPage = (url != null);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, PetMessages.BREED_NOT_FOUND_WITH_NAME + breedName);
    }

    // Helper Methods
    private String buildUrlWithId(String id) {
        return apiBaseUrl + BREEDS_URL + "/" + id;
    }

    private <T> T executeGetRequest(String url, Class<T> responseType) {
        try {
            return restTemplate.getForObject(url, responseType);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, PetMessages.BREED_NOT_FOUND_WITH_ID + url, e);
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, PetMessages.EXTERNAL_DOG_API_BREEDS_FAILURE, e);
            }
        } catch (RestClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, PetMessages.EXTERNAL_DOG_API_COMMUNICATION_FAILURE, e);
        }
    }

    private DogBreedDTO mapToBreedDTO(DogBreedByIdResponse response, String id) {
        if (response != null && response.getData() != null) {
            return DogBreedMapper.INSTANCE.toBreedDTO(response.getData());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, PetMessages.BREED_NOT_FOUND_WITH_ID + id);
        }
    }

    private void addBreedNamesToList(DogBreedResponse response, List<String> allBreedsNames) {
        if (response != null && response.getData() != null) {
            response.getData().forEach(dogBreed -> allBreedsNames.add(dogBreed.getAttributes().getName()));
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, PetMessages.EXTERNAL_DOG_API_BREEDS_NAMES_FAILURE);
        }
    }

    private String getNextPageUrl(DogBreedResponse response) {
        return (response.getLinks() != null && response.getLinks().getNext() != null) ? response.getLinks().getNext() : null;
    }

    private DogBreedDTO findBreedByName(DogBreedResponse response, String breedName) {
        if (response != null && response.getData() != null) {
            return response.getData().stream()
                    .filter(dogBreed -> dogBreed.getAttributes().getName().equalsIgnoreCase(breedName))
                    .map(DogBreedMapper.INSTANCE::toBreedDTO)
                    .findFirst()
                    .orElse(null);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, PetMessages.EXTERNAL_DOG_API_BREEDS_FAILURE);
        }
    }
}
