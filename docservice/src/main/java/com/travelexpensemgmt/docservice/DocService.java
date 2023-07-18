package com.travelexpensemgmt.docservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @project: travelexpensemgmt
 * @author: Hossein Rostamiraeini
 */
@Service
public class DocService {
    private final DocRepository docRepository;

    @Autowired
    public DocService(DocRepository docRepository) {
        this.docRepository = docRepository;
    }

    /**
     *
     * @param doc Object to be saved in DB
     * @return the saved Object
     */
    public Doc saveDoc(Doc doc){
        final DocDbModel savedDoc = docRepository.save(createDocDbModel(doc));
        return createDoc(savedDoc);
    }

    /**
     *
     * @return a List of all Doc Objects existing in DB
     */
    public List<Doc> getDocs(){
        List<DocDbModel> docs = docRepository.findAll();
        return docs
                .stream()
                .map(this::createDoc)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param docId The id of the doc to be returned
     * @return A Doc with the given ID
     */
    public Doc getDocById(String docId) {
        final DocDbModel docDbModel = docRepository.findById(docId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found by id: " + docId));
        return createDoc(docDbModel);
    }
    /**
     * Delete a Doc by Id
     *
     * @param docId the unique Id of a Doc which is requiered for delete
     * @return the deleted Doc
     */
    public Doc deleteDocById(String docId){
        try {
            final Doc doc = getDocById(docId);
            docRepository.deleteById(docId);
            return doc;
        } catch (Exception e) {
            throw new IllegalArgumentException("Delete failed with error: " + e.getMessage());
        }
    }

    /**
     *
     * @param doc existing doc object to be edited
     * @return the updated Doc Object
     */
    public Doc updateById(Doc doc){
        if(docRepository.existsById(doc.getDocId())){
            final DocDbModel updatedDoc = docRepository.save(editDocDbModel(doc));
            return createDoc(updatedDoc);
        }else{
            throw new IllegalArgumentException("Doc not found by id: " + doc.getDocId());
        }
    }
    /**
     * INTERNAL
     * @param doc to be converted to a DB-Model Object
     * @return the converted object
     */
    private DocDbModel createDocDbModel(Doc doc) {
        return DocDbModel.builder()
                .id(null)
                .name(doc.getName())
                .docType(doc.getDocType())
                .url(doc.getUrl())
                .build();
    }

    /**
     * INTERNAL
     * @param doc to be converted to a DB-Model Object for editing purposes (The ID will be given too)
     * @return the converted object
     */
    private DocDbModel editDocDbModel(Doc doc) {
        return DocDbModel.builder()
                .id(doc.getDocId())
                .name(doc.getName())
                .docType(doc.getDocType())
                .url(doc.getUrl())
                .build();
    }

    /**
     * INTERNAL
     * @param docDbModel to be converted to a Task Object
     * @return the converted object
     */
    private Doc createDoc(DocDbModel docDbModel) {
        return Doc.builder()
                .docId(docDbModel.getId())
                .name(docDbModel.getName())
                .docType(docDbModel.getDocType())
                .url(docDbModel.getUrl())
                .build();
    }
}
