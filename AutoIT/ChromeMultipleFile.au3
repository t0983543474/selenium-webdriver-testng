WinWait("Open", "", "30")

Local $counter = 1

Do
	Send('""');
	Send($cmdLine[$counter]);
	Send('""');
	$counter =  $counter +1 ;

Until $counter = $cmdLine[0] + 1
Sleep(1000);

Send('{ENTER}')