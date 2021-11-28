//package pl.mkrew.app.service.bloodSuppliesService;
//
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BloodStatistics {
//
//    public static void main(String[] args) throws IOException {
//
//        Connection connection = Jsoup.connect("https://krew.info/zapasy/");
//        Document document = connection.get();
//
//        Elements allKrew = document.select("table");
//
//        List<BloodSupplies2> allBlood = new ArrayList<>();
//
//        for (Element element : allKrew) {
//            for (Element group : element.getElementsByTag("tr")) {
//                List<Element> cells = group.getElementsByTag("td");
//
//                if (cells.size() > 0) {
//                    //String name = group.getElementsByTag("td").first().text();
//
//                    for (Element city : group.getElementsByTag("tr")) {
//
//                        List<Element> blood = city.getElementsByTag("img");
//
//                        BloodSupplies2 bloodSupplies2 = new BloodSupplies2(cells.get(0).text(), blood.get(1).attr("src"), blood.get(2).attr("alt"));
//
//                        allBlood.add(bloodSupplies2);
//
//                        //System.out.println("Grupa krwi: " + name + "; Miasto: " + city.attr("alt") + "; poziom krwi: " + city.attr("src"));
//                    }
//                }
//            }
//        }
//        for (BloodSupplies2 bloodSupplies2 : allBlood) {
//            convertToJson(bloodSupplies2);
//        }
//    }
//    public static void convertToJson (BloodSupplies2 blood){
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            String json = mapper.writeValueAsString(blood);
//            System.out.println(json);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
//}
