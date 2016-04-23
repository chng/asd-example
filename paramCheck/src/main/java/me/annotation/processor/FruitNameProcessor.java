package me.annotation.processor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by chn on 15/12/15.
 */

@SupportedAnnotationTypes("FruitName")
public class FruitNameProcessor extends AbstractProcessor {

    private TypeElement typeElement;

    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        typeElement = env.getElementUtils().getTypeElement("me.annotation.FruitName");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(typeElement);
        for(Element element: elements) {
            processFruitName(element);
        }
        return false;
    }

    private void processFruitName(Element element) {
        List<? extends AnnotationMirror> annotations = element.getAnnotationMirrors();
        for(AnnotationMirror mirror: annotations) {
            Map<? extends ExecutableElement, ? extends AnnotationValue> values = mirror.getElementValues();
            String name = (String) getAnnotationValue(values, "name");
            processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "@_@ name="+name);
            System.err.println(name);
        }
    }

    private Object getAnnotationValue(Map<? extends ExecutableElement, ? extends AnnotationValue> values, String name) {
        for(Map.Entry<? extends ExecutableElement,? extends AnnotationValue> entry: values.entrySet()) {
            if(entry.getKey().getSimpleName().contentEquals(name)) {
                return entry.getValue().getValue();
            }
        }
        return null;
    }
}
