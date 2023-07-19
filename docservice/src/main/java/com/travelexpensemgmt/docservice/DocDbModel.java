package com.travelexpensemgmt.docservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;

/**
 * @project: travelexpensemgmt
 * @author: Hossein Rostamiraeini
 */
@Document(collection = "Doc")
@AllArgsConstructor
@Builder
@Getter
public class DocDbModel {
    @Id
    private String id;
    private String name;
    private DocType docType;
    @Indexed(unique = true)
    private URL url;

}
