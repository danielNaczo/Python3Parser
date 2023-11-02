package cpython;

import basic.BasicTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@RunWith(Parameterized.class)
public class ParameterizedTest extends BasicTest {

    public static String CPYTHON_DIRECTORY_PATH = "examples/cpython/";

    public ParameterizedTest(String testFileName) {
        this.testFileName = testFileName;
        this.pathPrefix = "";
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        Collection<Object[]> fileNames = new ArrayList<Object[]>();

        File cpythonDirectory = new File(CPYTHON_DIRECTORY_PATH);
        File[] dirListing = cpythonDirectory.listFiles();
        if (dirListing != null) {
            traverseDirectory(fileNames, dirListing);
        }

        return fileNames;
    }

    private static void traverseDirectory(Collection<Object[]> fileNames, File[] dirListing) {
        for (File child : dirListing) {
            if (child.isDirectory()) {
                traverseDirectory(fileNames, child.listFiles());
            } else if (child.isFile()) {
                fileNames.add(generateObjectArray(child.getPath()));
            }
        }
    }

    private static Object[] generateObjectArray(String fileName) {
        return new Object[]{fileName};
    }

    @Override
    public void setTestFileName() {
        //is set in constructor
    }

    @Override
    public void printInConsole() {
        //showInConsole = true;
    }

}
