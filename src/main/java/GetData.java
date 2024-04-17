
import EarthQuakesClasses.EarthQuakeOfTheWeek;
import core.Processor;
import data.DataFetcher;
import data.DataParser;
import data.StaticData;

class GetData {

    public static void getData() {

        try {
            DataFetcher dataFetcher = new DataFetcher();
            String jsonString = dataFetcher.fetch(StaticData.USER_API);

            DataParser dataParser = new DataParser();
            EarthQuakeOfTheWeek earthQuakeOfTheWeek = dataParser.parse(jsonString, EarthQuakeOfTheWeek.class);

            Processor processor = new Processor();
            processor.calculateSummary(earthQuakeOfTheWeek);

        } catch (Exception e) {
            System.out.println("Error was caught during processing. " + e.getMessage());
        }

    }

    public static void main(String[] args) {
        GetData.getData();
    }
}
