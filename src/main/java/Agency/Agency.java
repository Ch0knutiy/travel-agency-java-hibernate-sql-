package Agency;

import Entity.*;
import Find.*;
import Util.HibernateFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Agency {

    private HibernateFactory hibernateFactory;
    private CItyMapper cityEntity;
    private ClientMapper clientEntity;
    private CompanyMapper companyEntity;
    private CountryMapper countryEntity;
    private EmployeeMapper employeeEntity;
    private FlightsMapper flightsEntity;
    private HotelMapper hotelEntity;
    private RouteMapper routeEntity;
    private TicketMapper ticketEntity;
    private TransferMapper transferEntity;

    public Agency() throws IOException {
        createAgency();
    }


    private void createAgency() throws IOException{
        hibernateFactory = new HibernateFactory();
        cityEntity = new CItyMapper();
        clientEntity = new ClientMapper();
        companyEntity = new CompanyMapper();
        countryEntity = new CountryMapper();
        employeeEntity = new EmployeeMapper();
        flightsEntity = new FlightsMapper();
        hotelEntity = new HotelMapper();
        routeEntity = new RouteMapper();
        ticketEntity = new TicketMapper();
        transferEntity = new TransferMapper();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat tformat = new SimpleDateFormat("HH:mm:ss");
        boolean mainMenu = true;
        int answer = 0;
        int insideAnswer = 0;
        while (mainMenu) {
            MainMenu();
            try {
                answer = Integer.parseInt(write());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            switch (answer) {
                case 1:{
                    boolean insideMenu = true;
                    while (insideMenu) {
                        EntityMenu("CityEntity");

                        try {
                            insideAnswer = Integer.parseInt(write());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        switch (insideAnswer) {
                            case 1: {
                                List<CityEntity> cities = cityEntity.findAll();
                                System.out.println("Cities list");
                                cityEntity.printAllInfoCities(cities);

                                break;
                            }
                            case 2: {
                                List<CountryEntity> countryList = countryEntity.findAll();

                                CityEntity city = new CityEntity();
                                System.out.println("Add new city:");
                                System.out.println("Enter city:");
                                String cityName = write();
                                System.out.println("Enter the country from the table:");
                                countryEntity.printAll(countryList);
                                String countryId = write();
                                List<CountryEntity> countryGroupById = countryEntity.findById(Integer.parseInt(countryId));
                                city.setCity(cityName);
                                city.setCountryId(countryGroupById.get(0));
                                cityEntity.save(city);
                                break;
                            }
                            case 3: {

                                List<CityEntity> citiesList = cityEntity.findAll();
                                if (citiesList.isEmpty()) {
                                    System.out.println("You cannot edit a city, because there is no list of cities!");
                                    break;
                                }

                                System.out.println("Editing a city:");
                                System.out.println("Enter the city:");
                                System.out.println("(Enter 0 for exit)");
                                cityEntity.printAll(citiesList);

                                String idCity = write();
                                if (!isNumber(idCity)) {
                                    System.out.println("Exit");
                                    break;
                                }
                                if (Integer.parseInt(idCity) == 0) {
                                    System.out.println("Exit");
                                    break;
                                }
                                List<CityEntity> cityById = cityEntity.findById(Integer.parseInt(idCity));
                                if (cityById.isEmpty()) {
                                    System.out.println("Exit");
                                    break;
                                }
                                CityEntity city = cityById.get(0);

                                boolean boolEditCity = true;
                                while (boolEditCity) {
                                    CityEditMenu();
                                    String cityEditMenuOption = write();
                                    if (!isNumber(cityEditMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(cityEditMenuOption)) {
                                        case 1: {
                                            System.out.println("Old city:" + city.getCity());
                                            System.out.println("Add new city");
                                            String cityName = write();
                                            city.setCity(cityName);
                                            System.out.println("New city:" + city.getCity());
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Old country:" + city.getCountry().getCountry());
                                            System.out.println("Enter a country from the list:");
                                            System.out.println("(Enter 0 for exit)");
                                            List<CountryEntity> countryList = countryEntity.findAll();
                                            countryEntity.printAll(countryList);
                                            String idCountry = write();
                                            if (!isNumber(idCountry)) {
                                                System.out.println("Exit");
                                                break;
                                            }
                                            if (Integer.parseInt(idCountry) == 0) {
                                                System.out.println("Exit");
                                                break;
                                            }
                                            List<CountryEntity> countryGroupById = countryEntity.findById(Integer.parseInt(idCountry));
                                            if (countryGroupById.isEmpty()) {
                                                System.out.println("Exit");
                                                break;
                                            }

                                            city.setCountryId(countryGroupById.get(0));
                                            System.out.println("New country:" + city.getCountry().getCountry());
                                            break;
                                        }
                                        case 0: {
                                            boolEditCity = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }

                                    }
                                }
                                cityEntity.update(city);
                                break;
                            }

                            case 4: {
                                {
                                    List<CityEntity> citiesList = cityEntity.findAll();
                                    if (citiesList.isEmpty()) {
                                        System.out.println("You cannot delete a city because there is no list of cities!");
                                        break;
                                    }

                                    System.out.println("Delete City:");
                                    System.out.println("Enter the city from table:");
                                    System.out.println("(Enter 0 for exit)");
                                    cityEntity.printAll(citiesList);

                                    String idCity = write();
                                    if (!isNumber(idCity)) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    if (Integer.parseInt(idCity) == 0) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    List<CityEntity> cityById = cityEntity.findById(Integer.parseInt(idCity));
                                    if (cityById.isEmpty()) {
                                        System.out.println("Exit");
                                        break;
                                    }

                                    CityEntity city = cityById.get(0);


                                    cityEntity.delete(city);

                                    break;
                                }
                            }
                            case 5: {
                                List<CityEntity> citiesList = cityEntity.findAll();
                                if (citiesList.isEmpty()) {
                                    System.out.println("It is impossible to search for a city, because there is no list of cities!");
                                    break;
                                }

                                boolean boolFindCity = true;
                                while (boolFindCity) {
                                    CityFindMenu();
                                    String cityFindMenuOption = write();
                                    if (!isNumber(cityFindMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(cityFindMenuOption)) {
                                        case 1: {
                                            System.out.println("Enter city:");
                                            String cityName = write();

                                            List<CityEntity> cityByName = cityEntity.findByName(cityName);

                                            if (cityByName.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                cityEntity.printAllInfoCities(cityByName);
                                            }
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Enter country:");
                                            String countryName = write();
                                            List<CityEntity> cityByCountry = cityEntity.findByCountry(countryName);
                                            if (cityByCountry.isEmpty()) {
                                                System.out.println("Not found!");
                                            } else {
                                                System.out.println("Search result:");
                                                cityEntity.printAllInfoCities(cityByCountry);
                                            }
                                            break;

                                        }
                                        case 0: {
                                            boolFindCity = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                            case 0: {
                                insideMenu = false;
                                break;
                            }
                            default:
                            {
                                System.out.println("Incorrect!");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 2:{
                    boolean insideMenu = true;
                    while (insideMenu) {
                        EntityMenu("ClientEntity");

                        try {
                            insideAnswer = Integer.parseInt(write());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        switch (insideAnswer) {
                            case 1: {
                                List<ClientEntity> clients = clientEntity.findAll();
                                System.out.println("Clients list");
                                clientEntity.printAllInfoClients(clients);

                                break;
                            }
                            case 2: {
                                List<RouteEntity> routeList = routeEntity.findAll();

                                ClientEntity client = new ClientEntity();
                                System.out.println("Add new Client:");
                                System.out.println("Enter surname:");
                                String surname = write();
                                System.out.println("Enter name:");
                                String name = write();
                                System.out.println("Enter patronymic:");
                                String patronymic = write();
                                System.out.println("Enter phone:");
                                String phone = write();
                                System.out.println("Enter date_of_buy:");
                                String date_of_buy = write();
                                System.out.println("Enter time_of_buy:");
                                String time_of_buy = write();

                                System.out.println("Enter the route from the table:");
                                routeEntity.printAll(routeList);
                                String routeId = write();
                                List<RouteEntity> routeGroupById = routeEntity.findById(Integer.parseInt(routeId));
                                System.out.println(routeGroupById);
                                client.setSurname(surname);
                                client.setName(name);
                                client.setPatronymic(patronymic);
                                client.setPhone(phone);
                                try {
                                    client.setDateOfBuy(format.parse(date_of_buy));
                                }
                                catch (ParseException e){
                                    e.printStackTrace();
                                }
                                try {
                                    client.setTimeOfBuy(tformat.parse(time_of_buy));
                                }
                                catch (ParseException e){
                                    e.printStackTrace();
                                }
                                client.setRoute(routeGroupById.get(0));
                                clientEntity.save(client);
                                break;
                            }
                            case 3: {

                                List<ClientEntity> clientList = clientEntity.findAll();
                                if (clientList.isEmpty()) {
                                    System.out.println("You cannot edit a client, because there is no list of clients!");
                                    break;
                                }

                                System.out.println("Editing a client:");
                                System.out.println("Enter the client:");
                                System.out.println("(Enter 0 for exit)");
                                clientEntity.printAll(clientList);

                                String idClient = write();
                                if (!isNumber(idClient)) {
                                    System.out.println("Exit");
                                    break;
                                }
                                if (Integer.parseInt(idClient) == 0) {
                                    System.out.println("Exit");
                                    break;
                                }
                                List<ClientEntity> clientById = clientEntity.findById(Integer.parseInt(idClient));
                                if (clientById.isEmpty()) {
                                    System.out.println("Exit");
                                    break;
                                }
                                ClientEntity client = clientById.get(0);

                                boolean boolEditClient = true;
                                while (boolEditClient) {
                                    ClientEditMenu();
                                    String clientEditMenuOption = write();
                                    if (!isNumber(clientEditMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(clientEditMenuOption)) {
                                        case 1: {
                                            System.out.println("Old surname:" + client.getSurname());
                                            System.out.println("Add new surname");
                                            String surname = write();
                                            client.setSurname(surname);
                                            System.out.println("New surname:" + client.getSurname());
                                            break;
                                        }
                                        case 2:{
                                            System.out.println("Old name:" + client.getName());
                                            System.out.println("Add new name");
                                            String name = write();
                                            client.setName(name);
                                            System.out.println("New name:" + client.getName());
                                            break;
                                        }
                                        case 3:{
                                            System.out.println("Old patronymic:" + client.getPatronymic());
                                            System.out.println("Add new patronymic");
                                            String patronymic = write();
                                            client.setPatronymic(patronymic);
                                            System.out.println("New patronymic:" + client.getPatronymic());
                                            break;
                                        }
                                        case 4:{
                                            System.out.println("Old phone:" + client.getPhone());
                                            System.out.println("Add new phone");
                                            String phone = write();
                                            client.setPhone(phone);
                                            System.out.println("New phone:" + client.getPhone());
                                            break;
                                        }
                                        case 5:{
                                            System.out.println("Old date_of_buy:" + client.getDateOfBuy().toString());
                                            System.out.println("Add new date_of_buy");
                                            String date_of_buy = write();
                                            try {
                                                client.setDateOfBuy(format.parse(date_of_buy));
                                            }
                                            catch (ParseException e){
                                                e.printStackTrace();
                                            }
                                            System.out.println("New date_of_buy:" + client.getDateOfBuy().toString());
                                            break;
                                        }
                                        case 6:{
                                            System.out.println("Old time_of_buy:" + client.getTimeOfBuy().toString());
                                            System.out.println("Add new time_of_buy");
                                            String time_of_buy = write();
                                            try {
                                                client.setTimeOfBuy(tformat.parse(time_of_buy));
                                            }
                                            catch (ParseException e){
                                                e.printStackTrace();
                                            }
                                            System.out.println("New time_of_buy:" + client.getTimeOfBuy().toString());
                                            break;
                                        }
                                        case 7: {
                                            System.out.println("Old route:" + client.getRoute().getName());
                                            System.out.println("Enter a route from the list:");
                                            System.out.println("(Enter 0 for exit)");
                                            List<RouteEntity> routeList = routeEntity.findAll();
                                            routeEntity.printAll(routeList);
                                            String idRoute = write();
                                            if (!isNumber(idRoute)) {
                                                System.out.println("Exit");
                                                break;
                                            }
                                            if (Integer.parseInt(idRoute) == 0) {
                                                System.out.println("Exit");
                                                break;
                                            }
                                            List<RouteEntity> routeGroupById = routeEntity.findById(Integer.parseInt(idRoute));
                                            if (routeGroupById.isEmpty()) {
                                                System.out.println("Exit");
                                                break;
                                            }

                                            client.setRoute(routeGroupById.get(0));
                                            System.out.println("New route:" + client.getRoute().getName());
                                            break;
                                        }
                                        case 0: {
                                            boolEditClient = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }

                                    }
                                }
                                clientEntity.update(client);
                                break;
                            }

                            case 4: {
                                {
                                    List<ClientEntity> clientList = clientEntity.findAll();
                                    if (clientList.isEmpty()) {
                                        System.out.println("You cannot delete a client because there is no list of clients!");
                                        break;
                                    }

                                    System.out.println("Delete client:");
                                    System.out.println("Enter the client from table:");
                                    System.out.println("(Enter 0 for exit)");
                                    clientEntity.printAll(clientList);

                                    String idClient = write();
                                    if (!isNumber(idClient)) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    if (Integer.parseInt(idClient) == 0) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    List<ClientEntity> clientById = clientEntity.findById(Integer.parseInt(idClient));
                                    if (clientById.isEmpty()) {
                                        System.out.println("Exit");
                                        break;
                                    }

                                    ClientEntity client = clientById.get(0);
                                    clientEntity.delete(client);
                                    break;
                                }
                            }
                            case 5: {
                                List<ClientEntity> clientList = clientEntity.findAll();
                                if (clientList.isEmpty()) {
                                    System.out.println("It is impossible to search for a client, because there is no list of clients!");
                                    break;
                                }

                                boolean boolFindClient = true;
                                while (boolFindClient) {
                                    ClientFindMenu();
                                    String clientFindMenuOption = write();
                                    if (!isNumber(clientFindMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(clientFindMenuOption)) {
                                        case 1: {
                                            System.out.println("Enter surname:");
                                            String surname = write();

                                            List<ClientEntity> clientBySurname = clientEntity.findBySurname(surname);

                                            if (clientBySurname.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                clientEntity.printAllInfoClients(clientBySurname);
                                            }
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Enter Name:");
                                            String name = write();

                                            List<ClientEntity> clientByName = clientEntity.findByName(name);

                                            if (clientByName.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                clientEntity.printAllInfoClients(clientByName);
                                            }
                                            break;
                                        }
                                        case 3:{
                                            System.out.println("Enter Patronymic:");
                                            String patronymic = write();

                                            List<ClientEntity> clientByPatronymic = clientEntity.findByPatronymic(patronymic);

                                            if (clientByPatronymic.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                clientEntity.printAllInfoClients(clientByPatronymic);
                                            }
                                            break;
                                        }
                                        case 4:{
                                            System.out.println("Enter Phone:");
                                            String phone = write();

                                            List<ClientEntity> clientByPhone = clientEntity.findByPhone(phone);

                                            if (clientByPhone.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                clientEntity.printAllInfoClients(clientByPhone);
                                            }
                                            break;
                                        }
                                        case 5: {
                                            System.out.println("Enter route:");
                                            String routeName = write();
                                            List<ClientEntity> clientByRoute = clientEntity.findByRoute(routeName);
                                            if (clientByRoute.isEmpty()) {
                                                System.out.println("Not found!");
                                            } else {
                                                System.out.println("Search result:");
                                                clientEntity.printAllInfoClients(clientByRoute);
                                            }
                                            break;

                                        }
                                        case 0: {
                                            boolFindClient = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                            case 0: {
                                insideMenu = false;
                                break;
                            }
                            default:
                            {
                                System.out.println("Incorrect!");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 3:{
                    boolean insideMenu = true;
                    while (insideMenu) {
                        EntityMenu("CompanyEntity");

                        try {
                            insideAnswer = Integer.parseInt(write());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        switch (insideAnswer) {
                            case 1: {
                                List<CompanyEntity> companies = companyEntity.findAll();
                                System.out.println("companies list");
                                companyEntity.printAllInfoCompany(companies);
                                break;
                            }
                            case 2: {
                                List<FlightsEntity> flightList = flightsEntity.findAll();

                                CompanyEntity company = new CompanyEntity();
                                System.out.println("Add new company:");
                                System.out.println("Enter name:");
                                String name = write();
                                System.out.println("Enter the flight from the table:");
                                flightsEntity.printAll(flightList);
                                String flightNum = write();
                                List<FlightsEntity> flightGroupById = flightsEntity.findByNum(flightNum);
                                company.setName(name);
                                company.setNum(flightGroupById.get(0));
                                companyEntity.save(company);
                                break;
                            }
                            case 3: {

                                List<CompanyEntity> companyList = companyEntity.findAll();
                                if (companyList.isEmpty()) {
                                    System.out.println("You cannot edit a company, because there is no list of companies!");
                                    break;
                                }

                                System.out.println("Editing a company:");
                                System.out.println("Enter the company:");
                                System.out.println("(Enter 0 for exit)");
                                companyEntity.printAll(companyList);

                                String idCompany = write();
                                if (!isNumber(idCompany)) {
                                    System.out.println("Exit");
                                    break;
                                }
                                if (Integer.parseInt(idCompany) == 0) {
                                    System.out.println("Exit");
                                    break;
                                }
                                List<CompanyEntity> companyById = companyEntity.findById(Integer.parseInt(idCompany));
                                if (companyById.isEmpty()) {
                                    System.out.println("Exit");
                                    break;
                                }
                                CompanyEntity company = companyById.get(0);

                                boolean boolEditCompany = true;
                                while (boolEditCompany) {
                                    CompanyEditMenu();
                                    String companyEditMenuOption = write();
                                    if (!isNumber(companyEditMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(companyEditMenuOption)) {
                                        case 1:{
                                            System.out.println("Old name:" + company.getName());
                                            System.out.println("Add new name");
                                            String name = write();
                                            company.setName(name);
                                            System.out.println("New name:" + company.getName());
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Old flight:" + company.getNum().getNum());
                                            System.out.println("Enter a flight from the list:");
                                            System.out.println("(Enter 0 for exit)");
                                            List<FlightsEntity> flightList = flightsEntity.findAll();
                                            flightsEntity.printAll(flightList);
                                            String numFlight = write();
                                            if (numFlight == "0") {
                                                System.out.println("Exit");
                                                break;
                                            }
                                            List<FlightsEntity> flightGroupById = flightsEntity.findByNum(numFlight);
                                            if (flightGroupById.isEmpty()) {
                                                System.out.println("Exit");
                                                break;
                                            }

                                            company.setNum(flightGroupById.get(0));
                                            System.out.println("New flight:" + company.getNum().getNum());
                                            break;
                                        }
                                        case 0: {
                                            boolEditCompany = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }

                                    }
                                }
                                companyEntity.update(company);
                                break;
                            }

                            case 4: {
                                {
                                    List<CompanyEntity> companyList = companyEntity.findAll();
                                    if (companyList.isEmpty()) {
                                        System.out.println("You cannot delete a  company because there is no list of  companies!");
                                        break;
                                    }

                                    System.out.println("Delete  company:");
                                    System.out.println("Enter the  company from table:");
                                    System.out.println("(Enter 0 for exit)");
                                    companyEntity.printAll(companyList);

                                    String idCompany = write();
                                    if (!isNumber(idCompany)) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    if (Integer.parseInt(idCompany) == 0) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    List<CompanyEntity> companyById = companyEntity.findById(Integer.parseInt(idCompany));
                                    if (companyById.isEmpty()) {
                                        System.out.println("Exit");
                                        break;
                                    }

                                    CompanyEntity company = companyById.get(0);
                                    companyEntity.delete(company);
                                    break;
                                }
                            }
                            case 5: {
                                List<CompanyEntity> companyList = companyEntity.findAll();
                                if (companyList.isEmpty()) {
                                    System.out.println("It is impossible to search for a company, because there is no list of companies!");
                                    break;
                                }

                                boolean boolFindCompany = true;
                                while (boolFindCompany) {
                                    CompanyFindMenu();
                                    String companyFindMenuOption = write();
                                    if (!isNumber(companyFindMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(companyFindMenuOption)) {
                                        case 1: {
                                            System.out.println("Enter name:");
                                            String name = write();

                                            List<CompanyEntity> companyByName = companyEntity.findByName(name);

                                            if (companyByName.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                companyEntity.printAllInfoCompany(companyByName);
                                            }
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Enter flight_num:");
                                            String num = write();

                                            List<CompanyEntity> companyByNum = companyEntity.findByNum(num);

                                            if (companyByNum.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                companyEntity.printAllInfoCompany(companyByNum);
                                            }
                                            break;
                                        }
                                        case 0: {
                                            boolFindCompany = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                            case 0: {
                                insideMenu = false;
                                break;
                            }
                            default:
                            {
                                System.out.println("Incorrect!");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 4:{
                    boolean insideMenu = true;
                    while (insideMenu) {
                        EntityMenu("CountryEntity");

                        try {
                            insideAnswer = Integer.parseInt(write());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        switch (insideAnswer) {
                            case 1: {
                                List<CountryEntity> countries = countryEntity.findAll();
                                System.out.println("companies list");
                                countryEntity.printAllInfoCountries(countries);
                                break;
                            }
                            case 2: {
                                CountryEntity country = new CountryEntity();
                                System.out.println("Add new country:");
                                System.out.println("Enter name:");
                                String name = write();
                                country.setCountry(name);
                                countryEntity.save(country);
                                break;
                            }
                            case 3: {

                                List<CountryEntity> countryList = countryEntity.findAll();
                                if (countryList.isEmpty()) {
                                    System.out.println("You cannot edit a country, because there is no list of countries!");
                                    break;
                                }

                                System.out.println("Editing a country:");
                                System.out.println("Enter the country:");
                                System.out.println("(Enter 0 for exit)");
                                countryEntity.printAll(countryList);

                                String idCountry = write();
                                if (!isNumber(idCountry)) {
                                    System.out.println("Exit");
                                    break;
                                }
                                if (Integer.parseInt(idCountry) == 0) {
                                    System.out.println("Exit");
                                    break;
                                }
                                List<CountryEntity> countryById = countryEntity.findById(Integer.parseInt(idCountry));
                                if (countryById.isEmpty()) {
                                    System.out.println("Exit");
                                    break;
                                }
                                CountryEntity country = countryById.get(0);

                                boolean boolEditCountry = true;
                                while (boolEditCountry) {
                                    CountryEditMenu();
                                    String countryEditMenuOption = write();
                                    if (!isNumber(countryEditMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(countryEditMenuOption)) {
                                        case 1:{
                                            System.out.println("Old name:" + country.getCountry());
                                            System.out.println("Add new name");
                                            String name = write();
                                            country.setCountry(name);
                                            System.out.println("New name:" + country.getCountry());
                                            break;
                                        }
                                        case 0: {
                                            boolEditCountry = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }

                                    }
                                }
                                countryEntity.update(country);
                                break;
                            }

                            case 4: {
                                {
                                    List<CountryEntity> countryList = countryEntity.findAll();
                                    if (countryList.isEmpty()) {
                                        System.out.println("You cannot delete a  country because there is no list of  countries!");
                                        break;
                                    }

                                    System.out.println("Delete  country:");
                                    System.out.println("Enter the  country from table:");
                                    System.out.println("(Enter 0 for exit)");
                                    countryEntity.printAll(countryList);

                                    String idCountry = write();
                                    if (!isNumber(idCountry)) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    if (Integer.parseInt(idCountry) == 0) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    List<CountryEntity> countryById = countryEntity.findById(Integer.parseInt(idCountry));
                                    if (countryById.isEmpty()) {
                                        System.out.println("Exit");
                                        break;
                                    }

                                    CountryEntity country = countryById.get(0);
                                    countryEntity.delete(country);
                                    break;
                                }
                            }
                            case 5: {
                                List<CountryEntity> countryList = countryEntity.findAll();
                                if (countryList.isEmpty()) {
                                    System.out.println("It is impossible to search for a country, because there is no list of countries!");
                                    break;
                                }

                                boolean boolFindCountry = true;
                                while (boolFindCountry) {
                                    CountryFindMenu();
                                    String countryFindMenuOption = write();
                                    if (!isNumber(countryFindMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(countryFindMenuOption)) {
                                        case 1: {
                                            System.out.println("Enter country:");
                                            String name = write();

                                            List<CountryEntity> countryByName = countryEntity.findByName(name);

                                            if (countryByName.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                countryEntity.printAllInfoCountries(countryByName);
                                            }
                                            break;
                                        }
                                        case 0: {
                                            boolFindCountry = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                            case 0: {
                                insideMenu = false;
                                break;
                            }
                            default:
                            {
                                System.out.println("Incorrect!");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 5:{
                    boolean insideMenu = true;
                    while (insideMenu) {
                        EntityMenu("EmployeeEntity");

                        try {
                            insideAnswer = Integer.parseInt(write());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        switch (insideAnswer) {
                            case 1: {
                                List<EmployeeEntity> employees = employeeEntity.findAll();
                                System.out.println("Employees list");
                                employeeEntity.printAllInfoEmployees(employees);
                                break;
                            }
                            case 2: {
                                List<TransferEntity> transferList = transferEntity.findAll();

                                EmployeeEntity employee = new EmployeeEntity();
                                System.out.println("Add new employee:");
                                System.out.println("Enter surname:");
                                String surname = write();
                                System.out.println("Enter name:");
                                String name = write();
                                System.out.println("Enter patronymic:");
                                String patronymic = write();
                                System.out.println("Enter address:");
                                String address = write();
                                System.out.println("Enter birth:");
                                String birth = write();
                                System.out.println("Enter post:");
                                String post = write();
                                System.out.println("Enter salary:");
                                Integer salary = 0;
                                try {
                                    salary = Integer.parseInt(write());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                System.out.println("Enter the transfer from the table:");
                                transferEntity.printAll(transferList);
                                String transferId = write();
                                List<TransferEntity> transferById = transferEntity.findById(Integer.parseInt(transferId));
                                System.out.println("Enter phone:");
                                String phone = write();
                                employee.setSurname(surname);
                                employee.setName(name);
                                employee.setPatronymic(patronymic);
                                employee.setAddress(address);
                                try {
                                    employee.setBirth(format.parse(birth));
                                }
                                catch (ParseException e){
                                    e.printStackTrace();
                                }
                                employee.setPost(post);
                                employee.setSalary(salary);
                                employee.setTransferId(transferById.get(0));
                                employee.setPhone(phone);
                                employeeEntity.save(employee);
                                break;
                            }
                            case 3: {

                                List<EmployeeEntity> employeeList = employeeEntity.findAll();
                                if (employeeList.isEmpty()) {
                                    System.out.println("You cannot edit a employee, because there is no list of employees!");
                                    break;
                                }

                                System.out.println("Editing a employee:");
                                System.out.println("Enter the employee:");
                                System.out.println("(Enter 0 for exit)");
                                employeeEntity.printAll(employeeList);

                                String idEmployee = write();
                                if (!isNumber(idEmployee)) {
                                    System.out.println("Exit");
                                    break;
                                }
                                if (Integer.parseInt(idEmployee) == 0) {
                                    System.out.println("Exit");
                                    break;
                                }
                                List<EmployeeEntity> employeeById = employeeEntity.findById(Integer.parseInt(idEmployee));
                                if (employeeById.isEmpty()) {
                                    System.out.println("Exit");
                                    break;
                                }
                                EmployeeEntity employee = employeeById.get(0);

                                boolean boolEditEmployee = true;
                                while (boolEditEmployee) {
                                    EmployeeEditMenu();
                                    String employeeEditMenuOption = write();
                                    if (!isNumber(employeeEditMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(employeeEditMenuOption)) {
                                        case 1: {
                                            System.out.println("Old surname:" + employee.getSurname());
                                            System.out.println("Add new surname");
                                            String surname = write();
                                            employee.setSurname(surname);
                                            System.out.println("New surname:" + employee.getSurname());
                                            break;
                                        }
                                        case 2:{
                                            System.out.println("Old name:" + employee.getName());
                                            System.out.println("Add new name");
                                            String name = write();
                                            employee.setName(name);
                                            System.out.println("New name:" + employee.getName());
                                            break;
                                        }
                                        case 3:{
//                                          System.out.println("Old patronymic:" + employee.getPatronymic());
                                            System.out.println("Add new patronymic");
                                            String patronymic = write();
                                            employee.setPatronymic(patronymic);
                                            System.out.println("New patronymic:" + employee.getPatronymic());
                                            break;
                                        }
                                        case 4:{
                                            System.out.println("Old address:" + employee.getAddress());
                                            System.out.println("Add new address");
                                            String address = write();
                                            employee.setAddress(address);
                                            System.out.println("New address:" + employee.getAddress());
                                            break;
                                        }
                                        case 5:{
                                            System.out.println("Old birth:" + employee.getBirth().toString());
                                            System.out.println("Add new birth");
                                            String birth = write();
                                            try {
                                                employee.setBirth(format.parse(birth));
                                            }
                                            catch (ParseException e){
                                                e.printStackTrace();
                                            }
                                            System.out.println("New birth:" + employee.getBirth().toString());
                                            break;
                                        }
                                        case 6:{
                                            System.out.println("Old post:" + employee.getPost());
                                            System.out.println("Add new post");
                                            String post = write();
                                            employee.setPost(post);
                                            System.out.println("New post:" + employee.getPost());
                                            break;
                                        }
                                        case 7: {
                                            System.out.println("Old salary:" + employee.getSalary());
                                            System.out.println("Add new salary");
                                            Integer salary = 0;
                                            try {
                                                salary = Integer.parseInt(write());
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                            employee.setSalary(salary);
                                            System.out.println("New salary:" + employee.getSalary());
                                            break;
                                        }
                                        case 8: {
                                            System.out.println("Old transfer:" + employee.getTransferId().getNum());
                                            System.out.println("Enter a transfer from the list:");
                                            System.out.println("(Enter 0 for exit)");
                                            List<TransferEntity> transferList = transferEntity.findAll();
                                            transferEntity.printAll(transferList);
                                            String idTransfer = write();
                                            if (!isNumber(idTransfer)) {
                                                System.out.println("Exit");
                                                break;
                                            }
                                            if (Integer.parseInt(idTransfer) == 0) {
                                                System.out.println("Exit");
                                                break;
                                            }
                                            List<TransferEntity> transferById = transferEntity.findById(Integer.parseInt(idTransfer));
                                            if (transferById.isEmpty()) {
                                                System.out.println("Exit");
                                                break;
                                            }
                                            employee.setTransferId(transferById.get(0));
                                            System.out.println("New transfer:" + employee.getTransferId().getNum());
                                            break;
                                        }
                                        case 9:{
                                            System.out.println("Old phone:" + employee.getPhone());
                                            System.out.println("Add new phone");
                                            String phone = write();
                                            employee.setPhone(phone);
                                            System.out.println("New phone:" + employee.getPhone());
                                            break;
                                        }
                                        case 0: {
                                            boolEditEmployee = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }

                                    }
                                }
                                employeeEntity.update(employee);
                                break;
                            }
                            case 4: {
                                {
                                    List<EmployeeEntity> employeeList = employeeEntity.findAll();
                                    if (employeeList.isEmpty()) {
                                        System.out.println("You cannot delete a employee because there is no list of employees!");
                                        break;
                                    }

                                    System.out.println("Delete employee:");
                                    System.out.println("Enter the employee from table:");
                                    System.out.println("(Enter 0 for exit)");
                                    employeeEntity.printAll(employeeList);

                                    String idEmployee = write();
                                    if (!isNumber(idEmployee)) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    if (Integer.parseInt(idEmployee) == 0) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    List<EmployeeEntity> employeeById = employeeEntity.findById(Integer.parseInt(idEmployee));
                                    if (employeeById.isEmpty()) {
                                        System.out.println("Exit");
                                        break;
                                    }

                                    EmployeeEntity employee = employeeById.get(0);
                                    employeeEntity.delete(employee);
                                    break;
                                }
                            }
                            case 5: {
                                List<EmployeeEntity> employeeList = employeeEntity.findAll();
                                if (employeeList.isEmpty()) {
                                    System.out.println("It is impossible to search for a employee, because there is no list of employees!");
                                    break;
                                }

                                boolean boolFindEmployee = true;
                                while (boolFindEmployee) {
                                    EmployeeFindMenu();
                                    String employeeFindMenuOption = write();
                                    if (!isNumber(employeeFindMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(employeeFindMenuOption)) {
                                        case 1: {
                                            System.out.println("Enter surname:");
                                            String surname = write();

                                            List<EmployeeEntity> employeeBySurname = employeeEntity.findBySurname(surname);

                                            if (employeeBySurname.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                employeeEntity.printAllInfoEmployees(employeeBySurname);
                                            }
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Enter Name:");
                                            String name = write();

                                            List<EmployeeEntity> employeeByName = employeeEntity.findByName(name);

                                            if (employeeByName.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                employeeEntity.printAllInfoEmployees(employeeByName);
                                            }
                                            break;
                                        }
                                        case 3:{
                                            System.out.println("Enter Patronymic:");
                                            String patronymic = write();

                                            List<EmployeeEntity> employeeByPatronymic = employeeEntity.findByPatronymic(patronymic);

                                            if (employeeByPatronymic.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                employeeEntity.printAllInfoEmployees(employeeByPatronymic);
                                            }
                                            break;
                                        }
                                        case 4:{
                                            System.out.println("Enter address:");
                                            String address = write();

                                            List<EmployeeEntity> employeeByAddress = employeeEntity.findByAddress(address);

                                            if (employeeByAddress.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                employeeEntity.printAllInfoEmployees(employeeByAddress);
                                            }
                                            break;
                                        }
                                        case 5:{
                                            System.out.println("Enter post:");
                                            String post = write();

                                            List<EmployeeEntity> employeeByPost = employeeEntity.findByPost(post);

                                            if (employeeByPost.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                employeeEntity.printAllInfoEmployees(employeeByPost);
                                            }
                                            break;
                                        }
                                        case 6: {
                                            System.out.println("Enter transfer:");
                                            String transferNum = write();
                                            List<EmployeeEntity> employeeByTransfer = employeeEntity.findByTransfer(Integer.parseInt(transferNum));
                                            if (employeeByTransfer.isEmpty()) {
                                                System.out.println("Not found!");
                                            } else {
                                                System.out.println("Search result:");
                                                employeeEntity.printAllInfoEmployees(employeeByTransfer);
                                            }
                                            break;

                                        }
                                        case 7:{
                                            System.out.println("Enter Phone:");
                                            String phone = write();

                                            List<EmployeeEntity> employeeByPhone = employeeEntity.findByPhone(phone);

                                            if (employeeByPhone.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                employeeEntity.printAllInfoEmployees(employeeByPhone);
                                            }
                                            break;
                                        }
                                        case 0: {
                                            boolFindEmployee = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                            case 0: {
                                insideMenu = false;
                                break;
                            }
                            default:
                            {
                                System.out.println("Incorrect!");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 6:{
                    boolean insideMenu = true;
                    while (insideMenu) {
                        EntityMenu("FlightsEntity");

                        try {
                            insideAnswer = Integer.parseInt(write());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        switch (insideAnswer) {
                            case 1: {
                                List<FlightsEntity> flights = flightsEntity.findAll();
                                System.out.println("Flights list");
                                flightsEntity.printAllInfoFlights(flights);
                                break;
                            }
                            case 2: {
                                FlightsEntity flight = new FlightsEntity();
                                System.out.println("Add new flight:");
                                System.out.println("Enter num:");
                                String num = write();
                                System.out.println("Enter date:");
                                String date = write();
                                System.out.println("Enter time:");
                                String time = write();
                                System.out.println("Enter aircraft:");
                                String aircraft = write();
                                System.out.println("Enter class:");
                                String clazz = write();
                                System.out.println("Enter free:");
                                Integer free = 0;
                                try {
                                    free = Integer.parseInt(write());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                flight.setNum(num);
                                try {
                                    flight.setDate((format.parse(date)));
                                }
                                catch (ParseException e){
                                    e.printStackTrace();
                                }
                                try {
                                    flight.setTime((tformat.parse(time)));
                                }
                                catch (ParseException e){
                                    e.printStackTrace();
                                }
                                flight.setAircraft(aircraft);
                                flight.setClazz(clazz);
                                flight.setFree(free);
                                flightsEntity.save(flight);
                                break;
                            }
                            case 3: {

                                List<FlightsEntity> flightsList = flightsEntity.findAll();
                                if (flightsList.isEmpty()) {
                                    System.out.println("You cannot edit a flight, because there is no list of flights!");
                                    break;
                                }

                                System.out.println("Editing a flight:");
                                System.out.println("Enter the flight:");
                                System.out.println("(Enter 0 for exit)");
                                flightsEntity.printAll(flightsList);

                                String idFlight = write();
                                if (idFlight == "0") {
                                    System.out.println("Exit");
                                    break;
                                }
                                List<FlightsEntity> flightsById = flightsEntity.findByNum(idFlight);
                                if (flightsById.isEmpty()) {
                                    System.out.println("Exit");
                                    break;
                                }
                                FlightsEntity flight = flightsById.get(0);

                                boolean boolEditFlight = true;
                                while (boolEditFlight) {
                                    FlightEditMenu();
                                    String flightEditMenuOption = write();
                                    if (!isNumber(flightEditMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(flightEditMenuOption)) {
                                        case 1:{
                                            System.out.println("Old date:" + flight.getDate().toString());
                                            System.out.println("Add new date");
                                            String date = write();
                                            try {
                                                flight.setDate(format.parse(date));
                                            }
                                            catch (ParseException e){
                                                e.printStackTrace();
                                            }
                                            System.out.println("New date:" + flight.getDate().toString());
                                            break;
                                        }
                                        case 2:{
                                            System.out.println("Old time:" + flight.getTime().toString());
                                            System.out.println("Add new time");
                                            String time = write();
                                            try {
                                                flight.setDate(tformat.parse(time));
                                            }
                                            catch (ParseException e){
                                                e.printStackTrace();
                                            }
                                            System.out.println("New time:" + flight.getTime().toString());
                                            break;
                                        }
                                        case 3:{
                                            System.out.println("Old aircraft:" + flight.getAircraft());
                                            System.out.println("Add new aircraft");
                                            String aircraft = write();
                                            flight.setAircraft(aircraft);
                                            System.out.println("New aircraft:" + flight.getAircraft());
                                            break;
                                        }
                                        case 4:{
                                            System.out.println("Old class:" + flight.getClazz());
                                            System.out.println("Add new class");
                                            String clazz = write();
                                            flight.setClazz(clazz);
                                            System.out.println("New class:" + flight.getClazz());
                                            break;
                                        }
                                        case 5:{
                                            System.out.println("Old free:" + flight.getFree());
                                            System.out.println("Add new free");
                                            Integer free = 0;
                                            try {
                                                free = Integer.parseInt(write());
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                            flight.setFree(free);
                                            System.out.println("New free:" + flight.getFree());
                                            break;
                                        }

                                        case 0: {
                                            boolEditFlight = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }

                                    }
                                }
                                flightsEntity.update(flight);
                                break;
                            }
                            case 4: {
                                {
                                    List<FlightsEntity> flightsList = flightsEntity.findAll();
                                    if (flightsList.isEmpty()) {
                                        System.out.println("You cannot delete a flight because there is no list of flights!");
                                        break;
                                    }

                                    System.out.println("Delete flight:");
                                    System.out.println("Enter the flight from table:");
                                    System.out.println("(Enter 0 for exit)");
                                    flightsEntity.printAll(flightsList);

                                    String idFlight = write();
                                    if (Integer.parseInt(idFlight) == 0) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    List<FlightsEntity> flightsById = flightsEntity.findByNum(idFlight);
                                    if (flightsById.isEmpty()) {
                                        System.out.println("Exit");
                                        break;
                                    }

                                    FlightsEntity flight = flightsById.get(0);
                                    flightsEntity.delete(flight);
                                    break;
                                }
                            }
                            case 5: {
                                List<FlightsEntity> flightsList = flightsEntity.findAll();
                                if (flightsList.isEmpty()) {
                                    System.out.println("It is impossible to search for a flight, because there is no list of flights!");
                                    break;
                                }

                                boolean boolFindFlights = true;
                                while (boolFindFlights) {
                                    FlightsFindMenu();
                                    String flightsFindMenuOption = write();
                                    if (!isNumber(flightsFindMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(flightsFindMenuOption)) {
                                        case 1: {
                                            System.out.println("Enter num:");
                                            String num = write();

                                            List<FlightsEntity> flightsByNum = flightsEntity.findByNum(num);

                                            if (flightsByNum.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                flightsEntity.printAllInfoFlights(flightsByNum);
                                            }
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Enter aircraft:");
                                            String aircraft = write();

                                            List<FlightsEntity> flightsByAircraft = flightsEntity.findByAircraft(aircraft);

                                            if (flightsByAircraft.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                flightsEntity.printAllInfoFlights(flightsByAircraft);
                                            }
                                            break;
                                        }
                                        case 3:{
                                            System.out.println("Enter class:");
                                            String clazz = write();

                                            List<FlightsEntity> flightsByClazz = flightsEntity.findByClass(clazz);

                                            if (flightsByClazz.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                flightsEntity.printAllInfoFlights(flightsByClazz);
                                            }
                                            break;
                                        }
                                        case 0: {
                                            boolFindFlights = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                            case 0: {
                                insideMenu = false;
                                break;
                            }
                            default:
                            {
                                System.out.println("Incorrect!");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 8:{
                    boolean insideMenu = true;
                    while (insideMenu) {
                        EntityMenu("RouteEntity");

                        try {
                            insideAnswer = Integer.parseInt(write());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        switch (insideAnswer) {
                            case 1: {
                                List<RouteEntity> routes = routeEntity.findAll();
                                System.out.println("routes list");
                                routeEntity.printAllInfoRoutes(routes);
                                break;
                            }
                            case 2: {
                                List<CityEntity> cityList = cityEntity.findAll();
                                List<HotelEntity> hotelList = hotelEntity.findAll();
                                List<CompanyEntity> companyList = companyEntity.findAll();
                                List<EmployeeEntity> employeeList = employeeEntity.findAll();

                                RouteEntity route = new RouteEntity();
                                System.out.println("Add new route:");
                                System.out.println("Enter name:");
                                String name = write();
                                System.out.println("Enter the city from the table:");
                                cityEntity.printAll(cityList);
                                String cityId = write();
                                List<CityEntity> cityGroupById = cityEntity.findById(cityId);
                                System.out.println("Enter duration:");
                                String duration = write();
                                System.out.println("Enter the hotel from the table:");
                                hotelEntity.printAll(hotelList);
                                String hotelId = write();
                                List<HotelEntity> hotelGroupById = hotelEntity.findById(hotelId);
                                System.out.println("Enter the company from the table:");
                                companyEntity.printAll(companyList);
                                String companyId = write();
                                List<CompanyEntity> CompanyGroupById = companyEntity.findById(companyId);
                                System.out.println("Enter the employee from the table:");
                                employeeEntity.printAll(employeeList);
                                String employeeId = write();
                                List<EmployeeEntity> employeeGroupById = employeeEntity.findById(employeeId);

                                route.setName(name);
                                route.setCityId(cityGroupById.get(0));
                                try {
                                    route.setDuration(tformat.parse(duration));
                                }
                                catch (ParseException e){
                                    e.printStackTrace();
                                }
                                route.setHotelId(hotelGroupById.get(0));
                                route.setCompanyId(CompanyGroupById.get(0));
                                route.setEmployeeId(employeeGroupById.get(0));

                                routeEntity.save(route);
                                break;
                            }
                            case 3: {

                                List<RouteEntity> routeList = routeEntity.findAll();
                                if (routeList.isEmpty()) {
                                    System.out.println("You cannot edit a route, because there is no list of routes!");
                                    break;
                                }

                                System.out.println("Editing a route:");
                                System.out.println("Enter the route:");
                                System.out.println("(Enter 0 for exit)");
                                routeEntity.printAll(routeList);

                                String idRoute = write();
                                if (!isNumber(idRoute)) {
                                    System.out.println("Exit");
                                    break;
                                }
                                if (Integer.parseInt(idRoute) == 0) {
                                    System.out.println("Exit");
                                    break;
                                }
                                List<RouteEntity> routeById = routeEntity.findById(Integer.parseInt(idRoute));
                                if (routeById.isEmpty()) {
                                    System.out.println("Exit");
                                    break;
                                }
                                RouteEntity route = routeById.get(0);

                                boolean boolEditRoute = true;
                                while (boolEditRoute) {
                                    RouteEditMenu();
                                    String routeEditMenuOption = write();
                                    if (!isNumber(routeEditMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(routeEditMenuOption)) {
                                        case 1:{
                                            System.out.println("Old name:" + route.getName());
                                            System.out.println("Add new name");
                                            String name = write();
                                            route.setName(name);
                                            System.out.println("New name:" + route.getName());
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Old city:" + route.getCityId().getCity());
                                            System.out.println("Enter a city from the list:");
                                            System.out.println("(Enter 0 for exit)");
                                            List<CityEntity> cityList = cityEntity.findAll();
                                            cityEntity.printAll(cityList);
                                            String numCity = write();
                                            if (!isNumber(numCity)) {
                                                System.out.println("Exit");
                                                break;
                                            }
                                            if (Integer.parseInt(numCity) == 0) {
                                                System.out.println("Exit");
                                                break;
                                            }
                                            List<CityEntity> CityById = cityEntity.findById(numCity);
                                            if (CityById.isEmpty()) {
                                                System.out.println("Exit");
                                                break;
                                            }

                                            route.setCityId(CityById.get(0));
                                            System.out.println("New city:" + route.getCityId().getCity());
                                            break;
                                        }
                                        case 3:{
                                            System.out.println("Old duration:" + route.getDuration().toString());
                                            System.out.println("Add new duration");
                                            String duration = write();
                                            try {
                                                route.setDuration(tformat.parse(duration));
                                            }
                                            catch (ParseException e){
                                                e.printStackTrace();
                                            }
                                            System.out.println("New duration:" + route.getDuration().toString());
                                            break;
                                        }
                                        case 4: {
                                            System.out.println("Old hotel:" + route.getHotelId().getName());
                                            System.out.println("Enter a hotel from the list:");
                                            System.out.println("(Enter 0 for exit)");
                                            List<HotelEntity> hotelList = hotelEntity.findAll();
                                            hotelEntity.printAll(hotelList);
                                            String numHotel = write();
                                            if (!isNumber(numHotel)) {
                                                System.out.println("Exit");
                                                break;
                                            }
                                            if (Integer.parseInt(numHotel) == 0) {
                                                System.out.println("Exit");
                                                break;
                                            }
                                            List<HotelEntity> hotelById = hotelEntity.findById(numHotel);
                                            if (hotelById.isEmpty()) {
                                                System.out.println("Exit");
                                                break;
                                            }

                                            route.setHotelId(hotelById.get(0));
                                            System.out.println("New hotel:" + route.getHotelId().getName());
                                            break;
                                        }
                                        case 5:{
                                            System.out.println("Old company:" + route.getCompanyId().getName());
                                            System.out.println("Enter a company from the list:");
                                            System.out.println("(Enter 0 for exit)");
                                            List<CompanyEntity> companyList = companyEntity.findAll();
                                            companyEntity.printAll(companyList);
                                            String numCompany = write();
                                            if (!isNumber(numCompany)) {
                                                System.out.println("Exit");
                                                break;
                                            }
                                            if (Integer.parseInt(numCompany) == 0) {
                                                System.out.println("Exit");
                                                break;
                                            }
                                            List<CompanyEntity> companyById = companyEntity.findById(numCompany);
                                            if (companyById.isEmpty()) {
                                                System.out.println("Exit");
                                                break;
                                            }

                                            route.setCompanyId(companyById.get(0));
                                            System.out.println("New company:" + route.getCompanyId().getName());
                                            break;
                                        }
                                        case 6:{
                                            System.out.println("Old employee:" + route.getEmployeeId().getName());
                                            System.out.println("Enter a employee from the list:");
                                            System.out.println("(Enter 0 for exit)");
                                            List<EmployeeEntity> employeeList = employeeEntity.findAll();
                                            employeeEntity.printAll(employeeList);
                                            String numEmployee = write();
                                            if (!isNumber(numEmployee)) {
                                                System.out.println("Exit");
                                                break;
                                            }
                                            if (Integer.parseInt(numEmployee) == 0) {
                                                System.out.println("Exit");
                                                break;
                                            }
                                            List<EmployeeEntity> employeeById = employeeEntity.findById(numEmployee);
                                            if (employeeById.isEmpty()) {
                                                System.out.println("Exit");
                                                break;
                                            }

                                            route.setEmployeeId(employeeById.get(0));
                                            System.out.println("New employee:" + route.getEmployeeId().getName());
                                            break;
                                        }
                                        case 0: {
                                            boolEditRoute = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }

                                    }
                                }
                                routeEntity.update(route);
                                break;
                            }

                            case 4: {
                                {
                                    List<RouteEntity> routeList = routeEntity.findAll();
                                    if (routeList.isEmpty()) {
                                        System.out.println("You cannot delete a  route because there is no list of  routes!");
                                        break;
                                    }

                                    System.out.println("Delete  route:");
                                    System.out.println("Enter the  route from table:");
                                    System.out.println("(Enter 0 for exit)");
                                    routeEntity.printAll(routeList);

                                    String idRoute = write();
                                    if (!isNumber(idRoute)) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    if (Integer.parseInt(idRoute) == 0) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    List<RouteEntity> routeById = routeEntity.findById(Integer.parseInt(idRoute));
                                    if (routeById.isEmpty()) {
                                        System.out.println("Exit");
                                        break;
                                    }

                                    RouteEntity route = routeById.get(0);
                                    routeEntity.delete(route);
                                    break;
                                }
                            }
                            case 5: {
                                List<RouteEntity> routeList = routeEntity.findAll();
                                if (routeList.isEmpty()) {
                                    System.out.println("It is impossible to search for a route, because there is no list of routes!");
                                    break;
                                }

                                boolean boolFindRoute = true;
                                while (boolFindRoute) {
                                    RouteFindMenu();
                                    String routeFindMenuOption = write();
                                    if (!isNumber(routeFindMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(routeFindMenuOption)) {
                                        case 1: {
                                            System.out.println("Enter name:");
                                            String name = write();

                                            List<RouteEntity> routeByName = routeEntity.findByName(name);

                                            if (routeByName.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                routeEntity.printAllInfoRoutes(routeByName);
                                            }
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Enter city:");
                                            String city = write();

                                            List<RouteEntity> routeByCity = routeEntity.findByCity(city);

                                            if (routeByCity.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                routeEntity.printAllInfoRoutes(routeByCity);
                                            }
                                            break;
                                        }
                                        case 3: {
                                            System.out.println("Enter hotel:");
                                            String name = write();

                                            List<RouteEntity> companyByName = routeEntity.findByHotel(name);

                                            if (companyByName.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                routeEntity.printAllInfoRoutes(companyByName);
                                            }
                                            break;
                                        }
                                        case 4: {
                                            System.out.println("Enter compny:");
                                            String name = write();

                                            List<RouteEntity> companyByName = routeEntity.findByCompany(name);

                                            if (companyByName.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                routeEntity.printAllInfoRoutes(companyByName);
                                            }
                                            break;
                                        }
                                        case 5: {
                                            System.out.println("Enter employee:");
                                            String name = write();

                                            List<RouteEntity> companyByName = routeEntity.findByEmployee(name);

                                            if (companyByName.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                routeEntity.printAllInfoRoutes(companyByName);
                                            }
                                            break;
                                        }
                                        case 0: {
                                            boolFindRoute = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                            case 0: {
                                insideMenu = false;
                                break;
                            }
                            default:
                            {
                                System.out.println("Incorrect!");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 7:{
                    boolean insideMenu = true;
                    while (insideMenu) {
                        EntityMenu("HotelEntity");

                        try {
                            insideAnswer = Integer.parseInt(write());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        switch (insideAnswer) {
                            case 1: {
                                List<HotelEntity> hotels = hotelEntity.findAll();
                                System.out.println("Hotels list:");
                                hotelEntity.printAllInfoHotels(hotels);

                                break;
                            }
                            case 2: {
                                HotelEntity hotels = new HotelEntity();

                                System.out.println("Add new hotel name:");
                                String hotel_name = write();

                                System.out.println("Enter class:");
                                String clazz = write();

                                System.out.println("Enter categories:");

                                String categories = write();


                                hotels.setName(hotel_name);
                                hotels.setClazz(Integer.parseInt(clazz));
                                hotels.setCategories(categories);
                                hotelEntity.save(hotels);
                                break;
                            }
                            case 3: {

                                List<HotelEntity> hotels = hotelEntity.findAll();
                                if (hotels.isEmpty()) {
                                    System.out.println("You cannot edit a hotels, because there is no list of hotels!");
                                    break;
                                }

                                System.out.println("Editing a hotels:");
                                System.out.println("Enter the hotels:");
                                System.out.println("(Enter 0 for exit)");
                                hotelEntity.printAll(hotels);

                                String idTransfer = write();
                                if (!isNumber(idTransfer)) {
                                    System.out.println("Exit");
                                    break;
                                }
                                if (Integer.parseInt(idTransfer) == 0) {
                                    System.out.println("Exit");
                                    break;
                                }
                                List<HotelEntity> transferById = hotelEntity.findById(Integer.parseInt(idTransfer));
                                if (transferById.isEmpty()) {
                                    System.out.println("Exit");
                                    break;
                                }
                                HotelEntity hotel = transferById.get(0);

                                boolean boolEditHotel = true;
                                while (boolEditHotel) {
                                    HotelEditMenu();
                                    String hotelEditMenuOption = write();
                                    if (!isNumber(hotelEditMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(hotelEditMenuOption)) {
                                        case 1: {
                                            System.out.println("Old name:" + hotel.getName());
                                            System.out.println("Add new name");
                                            String routeId = write();
                                            hotel.setName(routeId);
                                            System.out.println("New num:" + hotel.getName());
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Old class:" + hotel.getClazz());
                                            System.out.println("Add new class");
                                            String seat = write();
                                            hotel.setClazz(Integer.parseInt(seat));
                                            System.out.println("New class:" + hotel.getClazz());
                                            break;
                                        }
                                        case 3: {

                                            System.out.println("Old categories:" + hotel.getCategories());
                                            System.out.println("Add new categories");
                                            String routeId = write();
                                            hotel.setCategories(routeId);
                                            System.out.println("New categories:" + hotel.getCategories());
                                            break;
                                        }
                                        case 0: {
                                            boolEditHotel = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }

                                    }
                                }
                                hotelEntity.update(hotel);
                                break;
                            }

                            case 4: {
                                {
                                    List<HotelEntity> hotels = hotelEntity.findAll();
                                    if (hotels.isEmpty()) {
                                        System.out.println("You cannot edit a hotels, because there is no list of hotels!");
                                        break;
                                    }

                                    System.out.println("Delete hotel");
                                    System.out.println("Enter the hotel from table:");
                                    System.out.println("(Enter 0 for exit)");
                                    hotelEntity.printAll(hotels);

                                    String idTransfer = write();
                                    if (!isNumber(idTransfer)) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    if (Integer.parseInt(idTransfer) == 0) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    List<HotelEntity> hotelById = hotelEntity.findById(Integer.parseInt(idTransfer));
                                    if (hotelById.isEmpty()) {
                                        System.out.println("Exit");
                                        break;
                                    }

                                    HotelEntity hotel = hotelById.get(0);


                                    hotelEntity.delete(hotel);

                                    break;
                                }
                            }
                            case 5: {
                                List<HotelEntity> hotels = hotelEntity.findAll();
                                if (hotels.isEmpty()) {
                                    System.out.println("You cannot edit a hotels, because there is no list of hotels!");
                                    break;
                                }

                                boolean boolFindHotel = true;
                                while (boolFindHotel) {
                                    HotelFindMenu();
                                    String hotelFindMenuOption = write();
                                    if (!isNumber(hotelFindMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(hotelFindMenuOption)) {
                                        case 1: {
                                            System.out.println("Enter name:");
                                            String transferNum = write();

                                            List<HotelEntity> ticketByNum = hotelEntity.findByName(transferNum);
                                            if (ticketByNum.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                hotelEntity.printAllInfoHotels(ticketByNum);
                                            }
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Enter class:");
                                            String transferNum = write();

                                            List<HotelEntity> ticketByClass = hotelEntity.findByClass(Integer.parseInt(transferNum));
                                            if (ticketByClass.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                hotelEntity.printAllInfoHotels(ticketByClass);
                                            }
                                            break;

                                        }
                                        case 3: {
                                            System.out.println("Enter categories:");
                                            String transferNum = write();

                                            List<HotelEntity> ticketByCategories = hotelEntity.findByCategories(transferNum);
                                            if (ticketByCategories.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                hotelEntity.printAllInfoHotels(ticketByCategories);
                                            }
                                            break;
                                        }
                                        case 0: {
                                            boolFindHotel = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                            case 0: {
                                insideMenu = false;
                                break;
                            }
                            default:
                            {
                                System.out.println("Incorrect!");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 9:{
                    boolean insideMenu = true;
                    while (insideMenu) {
                        EntityMenu("TicketEntity");

                        try {
                            insideAnswer = Integer.parseInt(write());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        switch (insideAnswer) {
                            case 1: {
                                List<TicketEntity> tickets = ticketEntity.findAll();
                                System.out.println("Tickets list:");
                                ticketEntity.printAllInfoTickets(tickets);

                                break;
                            }
                            case 2: {
                                List<FlightsEntity> flightsList = flightsEntity.findAll();
                                List<ClientEntity> clientsList = clientEntity.findAll();

                                TicketEntity ticket = new TicketEntity();

                                System.out.println("Add new flights_num:");
                                flightsEntity.printAll(flightsList);
                                String routeId = write();
                                List<FlightsEntity> flightById = flightsEntity.findByNum(routeId);

                                System.out.println("Enter seat:");
                                String ticketSeat = write();

                                System.out.println("Enter client:");
                                clientEntity.printAll(clientsList);
                                String clientId = write();
                                List<ClientEntity> clientById = clientEntity.findById(clientId);

                                ticket.setFlightNum(flightById.get(0));
                                ticket.setSeat(Integer.parseInt(ticketSeat));
                                ticket.setIdClient(clientById.get(0));
                                ticketEntity.save(ticket);
                                break;
                            }
                            case 3: {

                                List<TicketEntity> tickets = ticketEntity.findAll();
                                if (tickets.isEmpty()) {
                                    System.out.println("You cannot edit a tickets, because there is no list of tickets!");
                                    break;
                                }

                                System.out.println("Editing a ticket:");
                                System.out.println("Enter the ticket:");
                                System.out.println("(Enter 0 for exit)");
                                ticketEntity.printAll(tickets);

                                String idTransfer = write();
                                if (!isNumber(idTransfer)) {
                                    System.out.println("Exit");
                                    break;
                                }
                                if (Integer.parseInt(idTransfer) == 0) {
                                    System.out.println("Exit");
                                    break;
                                }
                                List<TicketEntity> transferById = ticketEntity.findById(Integer.parseInt(idTransfer));
                                if (transferById.isEmpty()) {
                                    System.out.println("Exit");
                                    break;
                                }
                                TicketEntity ticket = transferById.get(0);

                                boolean boolEditTicket = true;
                                while (boolEditTicket) {
                                    TicketEditMenu();
                                    String ticketEditMenuOption = write();
                                    if (!isNumber(ticketEditMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(ticketEditMenuOption)) {
                                        case 1: {
                                            List<FlightsEntity> flightsList = flightsEntity.findAll();
                                            System.out.println("Old num:" + ticket.getFlightNum());
                                            System.out.println("Add new num");
                                            flightsEntity.printAll(flightsList);
                                            String routeId = write();
                                            List<FlightsEntity> flightById = flightsEntity.findByNum(routeId);
                                            ticket.setFlightNum(flightById.get(0));
                                            System.out.println("New num:" + ticket.getFlightNum());
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Old seat:" + ticket.getSeat());
                                            System.out.println("Add new post");
                                            String seat = write();
                                            ticket.setSeat(Integer.parseInt(seat));
                                            System.out.println("New post:" + ticket.getSeat());
                                            break;
                                        }
                                        case 3: {
                                            List<ClientEntity> clientsList = clientEntity.findAll();
                                            System.out.println("Old client:" + ticket.getIdClient());
                                            System.out.println("Add new client");
                                            clientEntity.printAll(clientsList);
                                            String routeId = write();
                                            List<ClientEntity> clientById = clientEntity.findById(routeId);
                                            ticket.setIdClient(clientById.get(0));
                                            System.out.println("New client:" + ticket.getIdClient());
                                            break;
                                        }
                                        case 0: {
                                            boolEditTicket = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }

                                    }
                                }
                                ticketEntity.update(ticket);
                                break;
                            }

                            case 4: {
                                {
                                    List<TicketEntity> tickets = ticketEntity.findAll();
                                    if (tickets.isEmpty()) {
                                        System.out.println("You cannot edit a tickets, because there is no list of tickets!");
                                        break;
                                    }

                                    System.out.println("Delete ticket");
                                    System.out.println("Enter the ticket from table:");
                                    System.out.println("(Enter 0 for exit)");
                                    ticketEntity.printAll(tickets);

                                    String idTransfer = write();
                                    if (!isNumber(idTransfer)) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    if (Integer.parseInt(idTransfer) == 0) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    List<TicketEntity> ticketById = ticketEntity.findById(Integer.parseInt(idTransfer));
                                    if (ticketById.isEmpty()) {
                                        System.out.println("Exit");
                                        break;
                                    }

                                    TicketEntity ticket = ticketById.get(0);


                                    ticketEntity.delete(ticket);

                                    break;
                                }
                            }
                            case 5: {
                                List<TicketEntity> tickets = ticketEntity.findAll();
                                if (tickets.isEmpty()) {
                                    System.out.println("You cannot edit a tickets, because there is no list of tickets!");
                                    break;
                                }

                                boolean boolFindTicket = true;
                                while (boolFindTicket) {
                                    TicketFindMenu();
                                    String ticketFindMenuOption = write();
                                    if (!isNumber(ticketFindMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(ticketFindMenuOption)) {
                                        case 1: {
                                            System.out.println("Enter num:");
                                            String transferNum = write();

                                            List<TicketEntity> ticketByNum = ticketEntity.findByName(transferNum);
                                            if (ticketByNum.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                ticketEntity.printAllInfoTickets(ticketByNum);
                                            }
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Enter seat:");
                                            String seat = write();
                                            List<TicketEntity> ticketBySeat = ticketEntity.findBySeat(Integer.parseInt(seat));
                                            if (ticketBySeat.isEmpty()) {
                                                System.out.println("Not found!");
                                            } else {
                                                System.out.println("Search result:");
                                                ticketEntity.printAllInfoTickets(ticketBySeat);
                                            }
                                            break;

                                        }
                                        case 3: {
                                            System.out.println("Enter client:");
                                            String client = write();
                                            List<TicketEntity> ticketByClient = ticketEntity.findByClient(client);
                                            if (ticketByClient.isEmpty()) {
                                                System.out.println("Not found!");
                                            } else {
                                                System.out.println("Search result:");
                                                ticketEntity.printAllInfoTickets(ticketByClient);
                                            }
                                            break;

                                        }
                                        case 0: {
                                            boolFindTicket = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                            case 0: {
                                insideMenu = false;
                                break;
                            }
                            default:
                            {
                                System.out.println("Incorrect!");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 10:{
                    boolean insideMenu = true;
                    while (insideMenu) {
                        EntityMenu("TransferEntity");

                        try {
                            insideAnswer = Integer.parseInt(write());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        switch (insideAnswer) {
                            case 1: {
                                List<TransferEntity> tranfers = transferEntity.findAll();
                                System.out.println("Tranfers list:");
                                transferEntity.printAllInfoTransfers(tranfers);

                                break;
                            }
                            case 2: {
                                TransferEntity transfer = new TransferEntity();
                                System.out.println("Add new transfer:");
                                System.out.println("Enter num:");
                                String transferNum = write();
                                System.out.println("Enter post:");
                                String transferPost = write();
                                System.out.println("Enter reason:");
                                String transferReason = write();
                                System.out.println("Enter date:");
                                String transferDate = write();
                                transfer.setNum(Integer.parseInt(transferNum));
                                transfer.setPost(transferPost);
                                transfer.setReason(transferReason);

                                try {
                                    transfer.setDate(format.parse(transferDate));
                                }
                                catch (ParseException e){
                                    e.printStackTrace();
                                }

                                transferEntity.save(transfer);
                                break;
                            }
                            case 3: {

                                List<TransferEntity> transfersList = transferEntity.findAll();
                                if (transfersList.isEmpty()) {
                                    System.out.println("You cannot edit a transfer, because there is no list of transfers!");
                                    break;
                                }

                                System.out.println("Editing a transfer:");
                                System.out.println("Enter the transfer:");
                                System.out.println("(Enter 0 for exit)");
                                transferEntity.printAll(transfersList);

                                String idTransfer = write();
                                if (!isNumber(idTransfer)) {
                                    System.out.println("Exit");
                                    break;
                                }
                                if (Integer.parseInt(idTransfer) == 0) {
                                    System.out.println("Exit");
                                    break;
                                }
                                List<TransferEntity> transferById = transferEntity.findById(Integer.parseInt(idTransfer));
                                if (transferById.isEmpty()) {
                                    System.out.println("Exit");
                                    break;
                                }
                                TransferEntity transfer = transferById.get(0);

                                boolean boolEditTransfer = true;
                                while (boolEditTransfer) {
                                    TransferEditMenu();
                                    String transferEditMenuOption = write();
                                    if (!isNumber(transferEditMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(transferEditMenuOption)) {
                                        case 1: {
                                            System.out.println("Old num:" + transfer.getNum());
                                            System.out.println("Add new num");
                                            String num = write();
                                            transfer.setNum(Integer.parseInt(num));
                                            System.out.println("New num:" + transfer.getNum());
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Old post:" + transfer.getPost());
                                            System.out.println("Add new post");
                                            String post = write();
                                            transfer.setPost(post);
                                            System.out.println("New post:" + transfer.getPost());
                                            break;
                                        }
                                        case 3: {
                                            System.out.println("Old reason:" + transfer.getReason());
                                            System.out.println("Add new reason");
                                            String reason = write();
                                            transfer.setReason(reason);
                                            System.out.println("New reason:" + transfer.getReason());
                                            break;
                                        }
                                        case 4: {
                                            System.out.println("Old date:" + transfer.getReason());
                                            System.out.println("Add new date");
                                            String date = write();
                                            try {
                                                transfer.setDate(format.parse(date));
                                            }
                                            catch (ParseException e){
                                                e.printStackTrace();
                                            }
                                            System.out.println("New date:" + transfer.getDate());
                                            break;
                                        }
                                        case 0: {
                                            boolEditTransfer = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }

                                    }
                                }
                                transferEntity.update(transfer);
                                break;
                            }

                            case 4: {
                                {
                                    List<TransferEntity> transfersList = transferEntity.findAll();
                                    if (transfersList.isEmpty()) {
                                        System.out.println("You cannot delete a transfer because there is no list of transfers!");
                                        break;
                                    }

                                    System.out.println("Delete transfer:");
                                    System.out.println("Enter the transfer from table:");
                                    System.out.println("(Enter 0 for exit)");
                                    transferEntity.printAll(transfersList);

                                    String idTransfer = write();
                                    if (!isNumber(idTransfer)) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    if (Integer.parseInt(idTransfer) == 0) {
                                        System.out.println("Exit");
                                        break;
                                    }
                                    List<TransferEntity> transferById = transferEntity.findById(Integer.parseInt(idTransfer));
                                    if (transferById.isEmpty()) {
                                        System.out.println("Exit");
                                        break;
                                    }

                                    TransferEntity transfer = transferById.get(0);


                                    transferEntity.delete(transfer);

                                    break;
                                }
                            }
                            case 5: {
                                List<TransferEntity> transfersList = transferEntity.findAll();
                                if (transfersList.isEmpty()) {
                                    System.out.println("You cannot delete a transfer because there is no list of transfers!");
                                    break;
                                }

                                boolean boolFindTransfer = true;
                                while (boolFindTransfer) {
                                    TransferFindMenu();
                                    String transferFindMenuOption = write();
                                    if (!isNumber(transferFindMenuOption)) {
                                        System.out.println("Incorrect!");
                                        continue;
                                    }
                                    switch (Integer.parseInt(transferFindMenuOption)) {
                                        case 1: {
                                            System.out.println("Enter num:");
                                            String transferNum = write();

                                            List<TransferEntity> transferByNum = transferEntity.findByNum(Integer.parseInt(transferNum));
                                            if (transferByNum.isEmpty()) {
                                                System.out.println("Not found!.");
                                            } else {
                                                System.out.println("Search result:");
                                                transferEntity.printAllInfoTransfers(transferByNum);
                                            }
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Enter post:");
                                            String post = write();
                                            List<TransferEntity> transferByPost = transferEntity.findByPost(post);
                                            if (transferByPost.isEmpty()) {
                                                System.out.println("Not found!");
                                            } else {
                                                System.out.println("Search result:");
                                                transferEntity.printAllInfoTransfers(transferByPost);
                                            }
                                            break;

                                        }
                                        case 3: {
                                            System.out.println("Enter reason:");
                                            String reason = write();
                                            List<TransferEntity> transferByReason = transferEntity.findByPost(reason);
                                            if (transferByReason.isEmpty()) {
                                                System.out.println("Not found!");
                                            } else {
                                                System.out.println("Search result:");
                                                transferEntity.printAllInfoTransfers(transferByReason);
                                            }
                                            break;

                                        }
                                        case 0: {
                                            boolFindTransfer = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Incorrect!");
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                            case 0: {
                                insideMenu = false;
                                break;
                            }
                            default:
                            {
                                System.out.println("Incorrect!");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 0: {
                    mainMenu = false;
                    break;
                }
                default: {
                    System.out.println("Incorrect!");
                    break;
                }
            }
        }
    }

    public static String write() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        return reader.readLine();
    }

    private boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }

    private void MainMenu() {
        System.out.println("Travel Agency");
        System.out.println("Menu:");
        System.out.println("[0] Exit");
        System.out.println("[1] City");
        System.out.println("[2] Client");
        System.out.println("[3] Company");
        System.out.println("[4] Country");
        System.out.println("[5] Employee");
        System.out.println("[6] Flights");
        System.out.println("[7] Hotel");
        System.out.println("[8] Route");
        System.out.println("[9] Ticket");
        System.out.println("[10] Transfer");
        System.out.println("Enter the item:");
    }

    private void EntityMenu(String entityName) {
        System.out.println(entityName.toUpperCase());
        System.out.println("[1] Output information about all records");
        System.out.println("[2] Add new record");
        System.out.println("[3] Edit record");
        System.out.println("[4] Delete record");
        System.out.println("[5] Filter Search");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }

    private void CityEditMenu(){
        System.out.println("Choose what you want to edit:");
        System.out.println("[1] City");
        System.out.println("[2] Country");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }

    private void CityFindMenu(){
        System.out.println("Choose what you want to find:");
        System.out.println("[1] City by name");
        System.out.println("[2] City by country");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }

    private void ClientEditMenu(){
        System.out.println("Choose what you want to edit:");
        System.out.println("[1] Surname");
        System.out.println("[2] Name");
        System.out.println("[3] Patronymic");
        System.out.println("[4] Phone");
        System.out.println("[5] date_of_buy");
        System.out.println("[6] time_of_buy");
        System.out.println("[7] route");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }
    private void ClientFindMenu(){
        System.out.println("Choose what you want to find:");
        System.out.println("[1] Client by surname");
        System.out.println("[2] Client by name");
        System.out.println("[3] Client by patronymic");
        System.out.println("[4] Client by phone");
        System.out.println("[5] Client by route");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }

    private void CompanyEditMenu(){
        System.out.println("Choose what you want to edit:");
        System.out.println("[1] Name");
        System.out.println("[2] Flight_num");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }
    private void CompanyFindMenu(){
        System.out.println("Choose what you want to find:");
        System.out.println("[1] Company by Name");
        System.out.println("[2] Company by Flight_num");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }

    private  void CountryEditMenu(){
        System.out.println("Choose what you want to edit:");
        System.out.println("[1] Name");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }

    private void CountryFindMenu(){
        System.out.println("Choose what you want to find:");
        System.out.println("[1] Country by Name");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }

    private void EmployeeEditMenu(){
        System.out.println("Choose what you want to edit:");
        System.out.println("[1] Surname");
        System.out.println("[2] Name");
        System.out.println("[3] Patronymic");
        System.out.println("[4] address");
        System.out.println("[5] birth");
        System.out.println("[6] post");
        System.out.println("[7] salary");
        System.out.println("[8] transfer_id");
        System.out.println("[9] phone");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }
    private void EmployeeFindMenu(){
        System.out.println("Choose what you want to find:");
        System.out.println("[1] employee by Surname");
        System.out.println("[2] employee by Name");
        System.out.println("[3] employee by Patronymic");
        System.out.println("[4] employee by address");
        System.out.println("[5] employee by post");
        System.out.println("[6] employee by transfer");
        System.out.println("[7] employee by phone");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }
    private void FlightEditMenu(){
        System.out.println("Choose what you want to edit:");
        System.out.println("[1] date");
        System.out.println("[2] time");
        System.out.println("[3] aircraft");
        System.out.println("[4] class");
        System.out.println("[5] free");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }
    private void FlightsFindMenu(){
        System.out.println("Choose what you want to find:");
        System.out.println("[1] flights by num");
        System.out.println("[2] flights by aircraft");
        System.out.println("[3] flights by class");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }

    private void RouteEditMenu(){
        System.out.println("Choose what you want to edit:");
        System.out.println("[1] Name:");
        System.out.println("[2] City:");
        System.out.println("[3] Duration:");
        System.out.println("[4] Hotel:");
        System.out.println("[5] Company:");
        System.out.println("[6] Emloyee:");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }
    private void RouteFindMenu(){
        System.out.println("Choose what you want to find:");
        System.out.println("[1] Name:");
        System.out.println("[2] City:");
        System.out.println("[3] Hotel:");
        System.out.println("[4] Company:");
        System.out.println("[5] Emloyee:");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }
    private void HotelEditMenu(){
        System.out.println("Choose what you want to edit:");
        System.out.println("[1] Name");
        System.out.println("[2] Class");
        System.out.println("[3] Categories");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }

    private void HotelFindMenu(){
        System.out.println("Choose what you want to find:");
        System.out.println("[1] Hotel by name");
        System.out.println("[2] Hotel by class");
        System.out.println("[3] Hotel by categories");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }
    private void TicketEditMenu(){
        System.out.println("Choose what you want to edit:");
        System.out.println("[1] Flight num");
        System.out.println("[2] Seat");
        System.out.println("[3] Client");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }

    private void TicketFindMenu(){
        System.out.println("Choose what you want to find:");
        System.out.println("[1] Ticket by num of flights");
        System.out.println("[2] Ticket by seat");
        System.out.println("[3] Ticket by client");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }
    private void TransferEditMenu(){
        System.out.println("Choose what you want to edit:");
        System.out.println("[1] Num");
        System.out.println("[2] Post");
        System.out.println("[3] Reason");
        System.out.println("[4] Date");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }

    private void TransferFindMenu(){
        System.out.println("Choose what you want to find:");
        System.out.println("[1] Transfer by num");
        System.out.println("[2] Transfer by post");
        System.out.println("[3] Transfer by reason");
        System.out.println("[0] Back");
        System.out.println("Enter the item:");
    }
}
