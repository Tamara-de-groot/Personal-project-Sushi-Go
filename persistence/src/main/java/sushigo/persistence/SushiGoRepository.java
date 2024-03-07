package sushigo.persistence;

import sushigo.domain.ISushiGo;
import java.util.HashMap;
import java.util.Map;

public class SushiGoRepository implements ISushiGoRepository {

    private Map<String, ISushiGo> sushiGoHashMap;

    public SushiGoRepository() {
        this.sushiGoHashMap = new HashMap<>();
    }

    @Override
    public void save(String key, ISushiGo game) {
        sushiGoHashMap.put(key, game);
    }

    @Override
    public ISushiGo get(String key) {
        return sushiGoHashMap.get(key);
    }

}
