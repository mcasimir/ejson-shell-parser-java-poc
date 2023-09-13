package com.mongodb.aiprompttest;

import org.bson.BsonArray;
import org.bson.Document;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EjsonShellParser {
    public Document parseDocument(String shellCode) throws IOException {
        return Document.parse(parseAsEjsonString(shellCode));
    }

    public BsonArray parseArray(String shellCode) throws IOException {
        return BsonArray.parse(parseAsEjsonString(shellCode));
    }

    private String parseAsEjsonString(String shellCode) throws IOException {
        try (
                InputStream is = getClass().getClassLoader().getResourceAsStream("ejson-shell-parser.js");
                Context context = Context.newBuilder().allowAllAccess(true).build()
        ) {
            context.eval(Source.newBuilder("js", new InputStreamReader(is), "ejson-shell-parser.js").build());
            Value bindings = context.getBindings("js");
            Value defaultExport = bindings.getMember("globalThis").getMember("ejsonShellParser");
            return defaultExport.execute(shellCode).asString();
        }
    }
}
