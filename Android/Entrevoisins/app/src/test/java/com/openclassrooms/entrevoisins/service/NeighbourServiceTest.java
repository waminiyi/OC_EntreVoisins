package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    /**
     * Testing if neighbour listing is working fine
     */

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    /**
     * Testing if neighbour deleting is working fine
     */

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    /**
     * Testing if neighbour adding is working fine
     */
    @Test
    public void addNeighbourWithSuccess() {

        Neighbour neighbourToCreate = new Neighbour(
                System.currentTimeMillis(),
                "neighbour name",
                "https://i.pravatar.cc/150?u=" +System.currentTimeMillis(),
                "adress",
                "012345678",
                "about me"
        );
        service.createNeighbour(neighbourToCreate);

        List<Neighbour> neighboursList = service.getNeighbours();

        assertTrue(neighboursList.contains(neighbourToCreate));
    }

    /**Testing if adding neighbour to favorite is working
     *
     */

    @Test
    public void addNeighbourToFavoriteWithSuccess() {

        Neighbour neighbourToAddToFavorite=service.getNeighbours().get(0);
        service.addToFavorites(neighbourToAddToFavorite);
        assertTrue(service.getFavoritesNeighbours().contains(neighbourToAddToFavorite));
    }

}
