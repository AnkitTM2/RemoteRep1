	Sleep(3000);
	ControlFocus("File Upload","","Edit1");
	ControlClick("File Upload","","Edit1");
	ControlSetText("File Upload","","Edit1",$CmdLine[1]);
	Sleep(3000);
	ControlClick("File Upload","","Button1");
