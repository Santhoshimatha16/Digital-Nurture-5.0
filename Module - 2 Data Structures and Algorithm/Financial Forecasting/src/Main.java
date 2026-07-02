public class Main {

    public static double forecast(double currentValue,double growthRate,int years) {

        // Base Condition
        if(years == 0) {
            return currentValue;
        }
        // Recursive Call
        return forecast(currentValue * (1 + growthRate),growthRate, years - 1);
    }

    public static void main(String[] args) {

        double currentValue = 10000;

        double growthRate = 0.10; // 10%

        int years = 3;

        double futureValue =forecast(currentValue,growthRate,years);
        System.out.println("Future Value = ₹" +String.format("%.2f", futureValue));
    }
}