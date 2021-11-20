//package pl.mkrew.app.service.bloodSuppliesService;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.stereotype.Service;
//import pl.mkrew.app.dto.BloodSuppliesDto;
//import pl.mkrew.app.mapper.BloodSuppliesMapper;
//import pl.mkrew.app.repository.BloodSuppliesRepository;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class BloodSuppliesBialystok {
//
//    private static BloodSuppliesRepository bloodSuppliesRepository;
//    private final BloodSuppliesMapper bloodSuppliesMapper;
//
//    private Connection connection = Jsoup.connect("https://krew.info/zapasy/");
//    private Document document;
//
//
//    {
//        try {
//            document = connection.get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
////    public static List<String> getAllSupplies(Document document) {
////        Elements elements = document.select("td").select("img[src~=(?i)\\.(png|jpe?g|gif)]");
////        return bloodSuppliesRepository.findByBloodGroup()
////                .elements.stream()
////                .map(Element::mapToDto)
////                .collect(Collectors.toList());
////    }
//
//    public List<BloodSuppliesDto> getAllSupplies() {
//        Elements allBloodStatistics = document.select("td").select("img[src~=(?i)\\.(png|jpe?g|gif)]");
//        return bloodSuppliesRepository.findByBloodGroup()
//                .stream()
//                .map(bloodSuppliesMapper::mapToDto)
//                .collect(Collectors.toList());
//    }
////        for(Element element :allBloodStatistics) {
////        if (element.toString().contains("Białystok")) {
////            System.out.println(element.attr("src") + " city: " + element.attr("alt"));
////        }
////    }
//}
