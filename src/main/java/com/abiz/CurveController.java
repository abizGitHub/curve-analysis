package com.abiz;

import com.abiz.calculator.DequeManager;
import com.abiz.calculator.RepeatedNumbers;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;


@RestController
public class CurveController {

    ConcurrentHashMap<Integer, DequeManager> map = new ConcurrentHashMap<>();

    @PostMapping("/create")
    public String createCurveVariationCalc(@RequestBody int frameSize) {
        int index = map.size();
        map.put(index, new DequeManager(frameSize));
        return "post variables to /add/" + index;
    }

    @PostMapping("/add/{index}")
    public String addValue(@PathVariable int index, @RequestBody int[] values) {
        DequeManager manager = map.get(index);
        long largestVariation = manager.addValues(values);
        return "largest variation till now: " + largestVariation;
    }

    @GetMapping("/detail/{index}")
    public String getDetail(@PathVariable int index) {
        DequeManager manager = map.get(index);
        return String.format("max: %s ,min: %s ,largest variation: %s ,frameSize: %s ,counter: %s",
                manager.getRecordedMax(), manager.getRecordedMin(), manager.getLargestVariation(),
                manager.getFrameSize(), manager.getCounter()
        );
    }

    @PostMapping("/repeated-numbers") // [1-n]
    public int[] repeatedNumbers(@RequestBody int[] values) {
        RepeatedNumbers.extractRepeatedNumbers(values);
        return values;
    }

}
