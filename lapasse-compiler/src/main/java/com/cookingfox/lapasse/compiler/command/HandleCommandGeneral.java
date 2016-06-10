package com.cookingfox.lapasse.compiler.command;

import com.cookingfox.lapasse.annotation.HandleCommand;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import java.util.Arrays;
import java.util.Collections;

/**
 * Performs general validation of a {@link HandleCommand} annotated element.
 */
public class HandleCommandGeneral extends AbstractHandleCommand {

    protected boolean isAccessible = false;
    protected boolean isMethod = false;

    //----------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    //----------------------------------------------------------------------------------------------

    public HandleCommandGeneral(Element element) {
        super(element);
    }

    //----------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    //----------------------------------------------------------------------------------------------

    @Override
    public String getError() {
        String prefix = String.format("@%s annotated method", ANNOTATION);

        if (isValid()) {
            return String.format("%s is valid", prefix);
        }

        if (!isAccessible) {
            return String.format("%s must be a public or package-level, non-abstract, " +
                    "non-static method", prefix);
        } else if (!isMethod) {
            return String.format("@%s annotation can only be applied to methods", ANNOTATION);
        }

        return String.format("%s is invalid", prefix);
    }

    public ExecutableElement getExecutableElement() {
        return (ExecutableElement) element;
    }

    @Override
    public boolean isValid() {
        return isAccessible && isMethod;
    }

    //----------------------------------------------------------------------------------------------
    // PROTECTED METHODS
    //----------------------------------------------------------------------------------------------

    @Override
    protected void doProcess() {
        isAccessible = validateAccessibility();
        isMethod = validateKind();
    }

    protected boolean validateAccessibility() {
        // can not contain the following modifiers
        return Collections.disjoint(element.getModifiers(), Arrays.asList(
                Modifier.ABSTRACT,
                Modifier.NATIVE,
                Modifier.PROTECTED,
                Modifier.PROTECTED,
                Modifier.STATIC,
                Modifier.STRICTFP,
                Modifier.VOLATILE
        ));
    }

    protected boolean validateKind() {
        // annotation can only be applied to methods
        return element.getKind() == ElementKind.METHOD;
    }

    //----------------------------------------------------------------------------------------------
    // OBJECT OVERRIDES
    //----------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "HandleCommandGeneral{" +
                "isAccessible=" + isAccessible +
                ", isMethod=" + isMethod +
                '}';
    }

}
