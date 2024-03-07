package sushigo.persistence;

import sushigo.domain.ISushiGo;

public interface ISushiGoRepository {
    /**
     * Method to save a SushiGo game under a given key
     * 
     * @param key  The key under which the game should be saved
     * @param game The game to be saved
     */
    void save(String key, ISushiGo game);

    /**
     * Method to retrieve a SushiGo game under a given key
     * 
     * @param key The key for retrieval
     * @return The game saved under the key
     */
    ISushiGo get(String key);

}
