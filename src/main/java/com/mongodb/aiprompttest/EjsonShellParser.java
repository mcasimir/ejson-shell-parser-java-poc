package com.mongodb.aiprompttest;

import org.bson.Document;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EjsonShellParser {
    public Document parseEJSON(String shellCode) throws IOException {
        try (
                InputStream is = getClass().getClassLoader().getResourceAsStream("ejson-shell-parser.js");
                Context context = Context.newBuilder().allowAllAccess(true).build()
        ) {
            context.eval(Source.newBuilder("js", new InputStreamReader(is), "ejson-shell-parser.js").build());
            Value bindings = context.getBindings("js");
            Value defaultExport = bindings.getMember("globalThis").getMember("ejsonShellParser");
            String result = defaultExport.execute(shellCode).asString();
            return Document.parse(result);
        }
    }
}
