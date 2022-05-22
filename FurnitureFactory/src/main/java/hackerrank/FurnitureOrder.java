package hackerrank;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FurnitureOrder implements FurnitureOrderInterface {
    private final HashMap<Furniture,Integer> furnitureIntegerHashMap;

    /**
     * Initialize a new mapping of Furniture types to order quantities.
     */
    FurnitureOrder() {
        furnitureIntegerHashMap = new HashMap<>();
    }

    public void addToOrder(final Furniture type, final int furnitureCount) {
        Integer typeCount = 0;
        if(furnitureIntegerHashMap.containsKey(type)) {
            typeCount = furnitureIntegerHashMap.get(type);
        }
        furnitureIntegerHashMap.put(type, typeCount + furnitureCount);
    }

    public HashMap<Furniture, Integer> getOrderedFurniture() {
        return new HashMap<>(furnitureIntegerHashMap);
    }

    public float getTotalOrderCost() {
        AtomicInteger totalCost = new AtomicInteger();
        furnitureIntegerHashMap.entrySet().stream().forEach(furnitureData ->
                totalCost.addAndGet(Math.round(furnitureData.getValue() * furnitureData.getKey().cost())));
        return totalCost.get();
    }

    public int getTypeCount(Furniture type) {
        return furnitureIntegerHashMap.getOrDefault(type , 0);
    }

    public float getTypeCost(Furniture type) {
        return (type.cost() * (furnitureIntegerHashMap.containsKey(type) ? furnitureIntegerHashMap.get(type) : 0.0f));
    }

    public int getTotalOrderQuantity() {
        if(!furnitureIntegerHashMap.isEmpty()) {
            return furnitureIntegerHashMap.values().stream()
                    .reduce(Integer::sum)
                    .get();
        }else
            return 0;
    }
}