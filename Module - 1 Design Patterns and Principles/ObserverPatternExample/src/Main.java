public class Main {

    public static void main(String[] args) {

        StockMarket stockMarket =
                new StockMarket();

        Observer mobileUser =
                new MobileApp("Santhoshi");

        Observer webUser =
                new WebApp("Rahul");

        stockMarket.registerObserver(
                mobileUser);

        stockMarket.registerObserver(
                webUser);

        stockMarket.setStockPrice(
                "TCS",
                4200);
    }
}