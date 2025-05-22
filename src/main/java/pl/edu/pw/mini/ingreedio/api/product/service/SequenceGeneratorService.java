package pl.edu.pw.mini.ingreedio.api.product.service;


import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.ingreedio.api.product.model.DatabaseSequenceDocument;

@Service
@RequiredArgsConstructor
public class SequenceGeneratorService {
    private final MongoOperations mongoOperations;

    public Long generateSequence(String seqName) {
        Query query = new Query(Criteria.where("id").is(seqName));
        DatabaseSequenceDocument current = mongoOperations.findOne(query, DatabaseSequenceDocument.class);

        long seq = (current != null ? current.getSeq() : 0) + 1;
        Update update = new Update().set("seq", seq);
        mongoOperations.upsert(query, update, DatabaseSequenceDocument.class);

        return seq;
    }
}