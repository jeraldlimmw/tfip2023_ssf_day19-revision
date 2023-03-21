package sg.edu.nus.iss.day19revision.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.day19revision.model.LoveCalculator;
import sg.edu.nus.iss.day19revision.service.LoveCalculatorService;

@Controller
@RequestMapping
public class LoveCalculatorController {

    @Autowired
    private LoveCalculatorService lcSvc;

    @GetMapping(path="/love")
    public String getPercentage(@RequestParam(required=true) String fname, 
            @RequestParam(required=true) String sname, 
            Model m) throws IOException {

        LoveCalculator lc = new LoveCalculator(fname, sname);
        
        LoveCalculator result = this.lcSvc.getPercentage(lc);

        m.addAttribute("result", result);

        return "result";
    }

    @GetMapping(path="/love/list")
    public String getAllLoveCalc(Model model){
        List<LoveCalculator> allLoveCalc = lcSvc.getRecord();
        model.addAttribute("list", allLoveCalc);

        return "list";
    }    
}