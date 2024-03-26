import builder.CruiseBuilder;
import builder.CruiseShipBuilder;
import cruiseModel.*;
import cruisePackages.BasicPackage;
import cruisePackages.BeveragePackage;
import cruisePackages.WifiPackage;
import cruiseRooms.*;
import cruiseType.AdultsOnly;
import cruiseType.FamilyFriendly;
import factory.CruiseRoomFactory;
import org.junit.jupiter.api.*;
import strategy.CruisePackageStrategy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FinalProjectTests {

    @Nested
    @DisplayName("CruiseObjectCreation")
    public class TestCruiseManager {

        CruiseManager testCruiseManager;
        Client testClient;
        CruiseShip testCruiseShip;
        CruiseBuilder testCruiseBuilder;
        CruiseShipBuilder testCruiseShipBuilder;
        CruisePort testCruisePort;
        Cruise testCruise;

        @BeforeEach
        void setUp() {
            testCruiseManager = new CruiseManager();

        }

        @AfterEach
        void tearDown() {
            testCruiseManager = null;
        }

        @Test
        @DisplayName("CruiseManger Created")
        public void testCruiseManagerCreated() {
            assertNotNull(testCruiseManager);
        }

        @Test
        @DisplayName("CruiseShip Created")
        public void testCruiseShipCreated() {
            testCruiseShip = new CruiseShip("Parent Company", new String[]{"Ship 1", "Ship 2"});
            assertNotNull(testCruiseShip);
        }

        @Test
        @DisplayName("Cruise Created")
        public void testCruiseCreated() {
            testCruise = new Cruise(new CruisePort("testC", "TestN"));
            assertNotNull(testCruise);
        }

        @Test
        @DisplayName("Client Created")
        public void testClientCreated() {
            testClient = new Client("Austin");
            assertNotNull(testClient);
        }

        @Test
        @DisplayName("CruiseBuilder Created")
        public void testCruiseBuilderCreated() {
            testCruiseBuilder = new CruiseBuilder();
            assertNotNull(testCruiseBuilder);
        }

        @Test
        @DisplayName("CruiseShipBuilder Created")
        public void testCruiseShipBuilderCreated() {
            testCruiseShipBuilder = new CruiseShipBuilder();
            assertNotNull(testCruiseShipBuilder);
        }

        @Test
        @DisplayName("CruisePort Created")
        public void testCruisePortCreated() {
            testCruisePort = new CruisePort("testC", "TestN");
            assertNotNull(testCruisePort);
        }
    }

    @Nested
    @DisplayName("CruiseShip")
    public class TestCruiseShip {

        private CruiseShip cruiseShip;

        @BeforeEach
        void setUp() {
            this.cruiseShip = new CruiseShip("Parent Company", new String[]{"Ship 1", "Ship 2"});
            this.cruiseShip.setShipTypeStrategy(new AdultsOnly());
            this.cruiseShip.setPackages(Arrays.asList(new BasicPackage()));
            this.cruiseShip.setAssignedRoom(new CabinSuite(55));
        }

        @AfterEach
        void tearDown() {
            this.cruiseShip = null;
        }

        @Test
        @DisplayName("testGetParentCompany")
        public void testGetParentCompany() {
            Assertions.assertEquals("Parent Company", this.cruiseShip.getParentCompany());
        }

        @Test
        @DisplayName("testGetShipNames")
        public void testGetShipNames() {
            Assertions.assertEquals(Arrays.asList("Ship 1", "Ship 2"), this.cruiseShip.getShipNames());
        }

        @Test
        @DisplayName("testGetCosst")
        public void testGetCost() {
            Assertions.assertEquals("750.00", this.cruiseShip.getCost());
        }

        @Test
        @DisplayName("testGetShipType")
        public void testGetShipType() {
            Assertions.assertEquals("AdultsOnly", this.cruiseShip.getShipTypeStrategy().getClass().getSimpleName());
        }

        @Test
        @DisplayName("testPrintCost")
        public void testPrintCost() {
            PrintStream originalOut = System.out;

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();

            System.setOut(new PrintStream(outContent));

            this.cruiseShip.printCost();

            System.setOut(originalOut);

            Assertions.assertEquals("Total cost: $750.00" + System.lineSeparator(), outContent.toString());
        }
    }

    @Nested
    @DisplayName("CruisePackage")
    public class TestCruisePackage {

        WifiPackage wifiPackage;
        BeveragePackage beveragePackage;

        @Test
        @DisplayName("TestWifiPackageCost")
        public void testWifiPackageCost() {
            wifiPackage = new WifiPackage();
            DecimalFormat df = new DecimalFormat("#,###.00");
            Assertions.assertEquals("100.00", df.format(wifiPackage.calculatePrice()));
        }

        @Test
        @DisplayName("testWifiPackageDescription")
        public void testWifiPackageDescription() {
            wifiPackage = new WifiPackage();
            Assertions.assertEquals("Includes unlimited WIFI for the duration of your trip", wifiPackage.getDescription());
        }

        @Test
        @DisplayName("testEqualityOfSame")
        public void testEqualityOfSame() {
            wifiPackage = new WifiPackage();
            WifiPackage wifiPackage2 = new WifiPackage();
            Assertions.assertEquals(wifiPackage, wifiPackage2);
        }

        @Test
        @DisplayName("testEqualityOfDifferent")
        public void testEqualityOfDifferent() {
            wifiPackage = new WifiPackage();
            beveragePackage = new BeveragePackage();
            Assertions.assertNotEquals(wifiPackage, beveragePackage);
        }

        @Test
        @DisplayName("testHashCode")
        public void testHashCode() {
            wifiPackage = new WifiPackage();
            WifiPackage wifiPackage2 = new WifiPackage();
            Assertions.assertEquals(wifiPackage.hashCode(), wifiPackage2.hashCode());
        }

        @Test
        @DisplayName("testHashCodeOfDifferent")
        public void testHashCodeOfDifferent() {
            wifiPackage = new WifiPackage();
            beveragePackage = new BeveragePackage();
            Assertions.assertNotEquals(wifiPackage.hashCode(), beveragePackage.hashCode());
        }
    }

    @Nested
    @DisplayName("CruiseRoomTests")
    public class CruiseRoomTests {

        @Test
        @DisplayName("TestInsideCabinCreation")
        public void testInsideCabin() {
            InsideCabin room = new InsideCabin(1);
            Assertions.assertEquals("Inside Cabin", room.getRoomType());
        }

        @Test
        @DisplayName("TestInsideCabinsCost")
        public void testInsideCabinCost() {
            DecimalFormat df = new DecimalFormat("#,###.00");
            InsideCabin room = new InsideCabin(1);
            Assertions.assertEquals("300.00", df.format(room.getCost()));
        }

        @Test
        @DisplayName("TestCruiseRoomFactoryInsideCabin")
        public void testCruiseRoomFactoryInsideCabin(){
            CruiseRoomFactory factory = new CruiseRoomFactory();
            CruiseRoom room = factory.createCruiseRoom("Inside Cabin", 5);
            Assertions.assertTrue(room instanceof InsideCabin);
        }

        @Test
        @DisplayName("TestCruiseRoomFactoryCabinSuite")
        public void testCruiseRoomFactoryCabinSuite(){
            CruiseRoomFactory factory = new CruiseRoomFactory();
            CruiseRoom room = factory.createCruiseRoom("Cabin Suite", 5);
            Assertions.assertTrue(room instanceof CabinSuite);
        }

        @Test
        @DisplayName("TestCruiseRoomFactoryCabinWithBalcony")
        public void testCruiseRoomFactoryCabinWithBalcony(){
            CruiseRoomFactory factory = new CruiseRoomFactory();
            CruiseRoom room = factory.createCruiseRoom("Cabin with Balcony", 5);
            Assertions.assertTrue(room instanceof CabinWithBalcony);
        }

        @Test
        @DisplayName("TestCruiseRoomFactoryCabinWithWindow")
        public void testCruiseRoomFactoryCabinWithWindow(){
            CruiseRoomFactory factory = new CruiseRoomFactory();
            CruiseRoom room = factory.createCruiseRoom("Cabin with Window", 5);
            Assertions.assertTrue(room instanceof CabinWithWindow);
        }
    }

    @Nested
    @DisplayName("CruiseTypeStrategyTests")
    public class CruiseTypeStrategyTests {

        @Test
        @DisplayName("TestAdultsOnly")
        public void testAdultsOnly() {
            AdultsOnly adultsOnly = new AdultsOnly();
            Assertions.assertEquals("Adults Only", adultsOnly.getType());
        }

        @Test
        @DisplayName("TestFamilyFriendly")
        public void testFamilyFriendly() {
            FamilyFriendly familyFriendly = new FamilyFriendly();
            Assertions.assertEquals("Family Friendly", familyFriendly.getType());
        }
    }

}
