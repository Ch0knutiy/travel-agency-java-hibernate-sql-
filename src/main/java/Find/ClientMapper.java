package Find;

import Entity.ClientEntity;

import java.util.List;

public class ClientMapper extends MapperAbstract<ClientEntity>{
    @Override
    protected Class<ClientEntity> getType() {
        return ClientEntity.class;
    }

    @Override
    protected String getTableName() {
        return "client";
    }

    public void printAll(List<ClientEntity> clients) {
        for (ClientEntity client: clients) {
            System.out.println( client.getId() + ". " + client.getSurname() +" "+client.getName()+" "+client.getPatronymic());
        }
    }

    public void printAllInfoClients(List<ClientEntity> clients) {
        for (ClientEntity client: clients) {
            System.out.println("Client: "+client.getSurname()+" "+client.getName()+" "+client.getPatronymic());
            System.out.println("Phone: "+client.getPhone());
            System.out.println("date_of_buy: "+client.getDateOfBuy().toString()+" time_of_buy: "+client.getTimeOfBuy().toString());
            System.out.println("route: "+client.getRoute().getName());
            System.out.println("***");
        }
        System.out.println();
    }

    public List<ClientEntity> findByPhone(String phone) {
        return findByParameter(".byPhone","%" + phone + "%");
    }

    public List<ClientEntity> findByRoute(String routeName) {
        return findByParameter(".byRoute", "%" + routeName + "%");
    }
}
