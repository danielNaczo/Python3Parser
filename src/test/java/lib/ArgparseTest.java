package lib;
import basic.BasicTest;

public class ArgparseTest extends BasicTest{

	@Override
	public void setTestFileName(){
		//ATTENTION: this file was modified according to errors while
		//parsing function signatures like:
		//" def add_parser(self, name, **kwargs): "
		//With official Python3.6-grammar such constructs cannot be parsed
		testFileName = "lib/argparse.py";
	}

	@Override
	public void printInConsole() {
		//showInConsole = true;
	}
}