package com.travelexpensemgmt.docservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @project: travelexpensemgmt
 * @author: Hossein Rostamiraeini
 */
@RestController
@Slf4j
@RequestMapping("/api/v1/docs")
public class DocController {
    private final DocService docService;

    @Autowired
    public DocController(DocService docService)
    {
        this.docService = docService;
    }

    @GetMapping
    public ResponseEntity<List<Doc>> getDocs() {

        try {
            final List<Doc> docs = docService.getDocs();
            return new ResponseEntity<>(docs, HttpStatus.OK);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping
    public ResponseEntity<Doc> saveDoc(@RequestBody Doc doc) {
        try {
            return new ResponseEntity<>(docService.saveDoc(doc), HttpStatus.CREATED);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Doc> getDocById(@PathVariable("id") String id) {
        try {
            final Doc doc = docService.getDocById(id);
            return new ResponseEntity<>(doc, HttpStatus.OK);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Methode delete a Doc by Id
    @DeleteMapping("/delete/{docId}")
    public ResponseEntity<Doc> deleteDockbyId(@PathVariable("docId") String taskId) {
        try {
            Doc task = docService.deleteDocById(taskId);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Method update the docs attributes
    @PutMapping
    public ResponseEntity<Doc> updateById(@RequestBody Doc doc) {
        try {
            Doc updatedDoc = docService.updateById(doc);
            return new ResponseEntity<>(updatedDoc, HttpStatus.OK);
        }catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
