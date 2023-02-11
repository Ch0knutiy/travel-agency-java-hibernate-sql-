package Find;

import Entity.HotelEntity;

import java.util.List;

public class HotelMapper extends MapperAbstract<HotelEntity>{
    @Override
    protected Class<HotelEntity> getType() {
        return HotelEntity.class;
    }

    @Override
    protected String getTableName() {
        return "hotel";
    }

    public void printAll(List<HotelEntity> hotels) {
        for (HotelEntity hotel: hotels) {
            System.out.println( hotel.getId() + ". " + hotel.getName());
        }
    }

    public void printAllInfoHotels(List<HotelEntity> hotels) {
        for (HotelEntity hotel: hotels) {
            System.out.println("Name: "+hotel.getName());
            System.out.println("Class: "+hotel.getClazz());
            System.out.println("Categories: "+hotel.getCategories());
            System.out.println("***");
        }
        System.out.println();
    }

    public List<HotelEntity> findByCategories(String categories) {
        return findByParameter(".byCategories", "%" + categories + "%");
    }

    public List<HotelEntity> findByClass(int clazz) {
        return findByParameter(".byClass", clazz);
    }
}
