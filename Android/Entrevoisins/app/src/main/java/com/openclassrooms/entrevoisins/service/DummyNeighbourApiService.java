package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favourites= DummyNeighbourGenerator.getFavoritesNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
        DummyNeighbourGenerator.removeFromFavorites(neighbour);
        favourites=DummyNeighbourGenerator.getFavoritesNeighbours();
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void addToFavorites(Neighbour neighbour) {
        if (!favourites.contains(neighbour)){
            DummyNeighbourGenerator.addToFavorites(neighbour);
            favourites=DummyNeighbourGenerator.getFavoritesNeighbours();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getFavoritesNeighbours() {
        return favourites;
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */

    @Override
    public void deleteFromFavorites(Neighbour neighbour) {
        DummyNeighbourGenerator.removeFromFavorites(neighbour);
        favourites=DummyNeighbourGenerator.getFavoritesNeighbours();
    }
}
