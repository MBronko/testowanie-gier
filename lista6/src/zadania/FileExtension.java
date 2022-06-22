package zadania;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileExtension implements ParameterResolver {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface File {
        String path();
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.isAnnotated(File.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        if (parameterContext.getParameter().getType() != String.class) {
            throw new ParameterResolutionException("File content parameter has to be of String type");
        }
        String path = parameterContext.getParameter().getAnnotation(File.class).path();

        return getFileContent(path);
    }

    private String getFileContent(String path) {
        try {
            Path resolvedPath = Path.of(path);
            return Files.readString(resolvedPath);
        } catch (IOException e) {
            throw new ParameterResolutionException("File \"" + path + "\" doesn't exist");
        }
    }
}
