//package pl.mkrew.app.service.bloodSuppliesService;
//
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BloodStatistics3 {
//
//    public static void main(String[] args) throws IOException {
//
//        Document doc = Jsoup.connect("https://krew.info/zapasy/").get();
//
//        Element tbody = doc.getElementsByTag("tbody").first();
//
//        List<Element> blood = tbody.getElementsByTag("tr");
//
//        List<BloodSupplies2> allBlood = new ArrayList<>();
//
//        for (Element all: blood) {
//
//            List<Element> atrybut = all.getElementsByTag("img");
//
//            BloodSupplies2 bloodSupplies2 = new BloodSupplies2(blood.get(0).text(), atrybut.get(1).attr("src"), atrybut.get(2).text());
//
//            allBlood.add(bloodSupplies2);
//        }
//
//        for (BloodSupplies2 bloodSupplies2: allBlood) {
//            convertToJson(bloodSupplies2);
//            //System.out.println("grupa krwi: " + bloodSupplies2.getBloodGroup() + "; poziom krwi: " + bloodSupplies2.getBloodLevel());
//
//        }
//    }
//    public static void convertToJson(BloodSupplies2 blood) {
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
