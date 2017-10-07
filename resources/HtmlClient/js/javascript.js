function resetFunction() {
	try {
		// Reset forms
		document.getElementById("formIDVeranstalterrechnung").reset();
		document.getElementById("formIDVeranstalterrechnungExtended").reset();
		document.getElementById("formIDBuchungsprotkolle").reset();
		document.getElementById("formIDBuchungsprotkolleExtended").reset();
		document.getElementById("formIDAusgangsrechnungenKostenrechnungen")
				.reset();
		document.getElementById(
				"formIDAusgangsrechnungenKostenrechnungenExtended").reset();

		// Reset result
		var frame = document.getElementById("myIFrame");
		frameDoc = frame.contentDocument || frame.contentWindow.document;
		frameDoc.removeChild(frameDoc.documentElement);

	} catch (e) {
		// alert(e.message);
	}
}

function archive_Selected_DocumentType_SearchType() {

	// Reset input parameters and reset result
	resetFunction();

	var myDocumentType = document.getElementById("DocumentType");
	// 0 , 1 , 2 , 3
	var selectedIndexDocumentType = myDocumentType.selectedIndex;

	var mySearchType = document.getElementById("SearchType");
	// 0 or 1
	var selectedIndexSearchType = mySearchType.selectedIndex;

	// Define forms
	var veranstalterrechnung = document
			.getElementById("formIDVeranstalterrechnung");
	var veranstalterrechnungExtended = document
			.getElementById("formIDVeranstalterrechnungExtended");

	var buchungsprotkolle = document.getElementById("formIDBuchungsprotkolle");
	var buchungsprotkolleExtended = document
			.getElementById("formIDBuchungsprotkolleExtended");

	var formIDAusgangsrechnungenKostenrechnungen = document
			.getElementById("formIDAusgangsrechnungenKostenrechnungen");
	var formIDAusgangsrechnungenKostenrechnungenExtended = document
			.getElementById("formIDAusgangsrechnungenKostenrechnungenExtended");

	var midocoBelege = document.getElementById("formIDMidocoBelege");
	var midocoBelegeExtended = document
			.getElementById("formIDMidocoBelegeExtended");

	// Clear All forms
	veranstalterrechnung.style.display = "none";
	veranstalterrechnungExtended.style.display = "none";
	buchungsprotkolle.style.display = "none";
	buchungsprotkolleExtended.style.display = "none";
	formIDAusgangsrechnungenKostenrechnungen.style.display = "none";
	formIDAusgangsrechnungenKostenrechnungenExtended.style.display = "none";
	midocoBelege.style.display = "none";
	midocoBelegeExtended.style.display = "none";
	
	// Clear actions
	formIDAusgangsrechnungenKostenrechnungen.action = "";
	formIDAusgangsrechnungenKostenrechnungenExtended.action = "";

	switch (selectedIndexDocumentType) {
	case 0:
		if (selectedIndexSearchType == 0) {// 0/0
			formIDAusgangsrechnungenKostenrechnungen.style.display = "block";
			formIDAusgangsrechnungenKostenrechnungen.action = "/archive/AusgangsrechnungenArchive";
			// alert("set action to"
			// + formIDAusgangsrechnungenKostenrechnungen.action);
		} else {// 0/1
			formIDAusgangsrechnungenKostenrechnungenExtended.style.display = "block";
			formIDAusgangsrechnungenKostenrechnungenExtended.action = "/archive/AusgangsrechnungenArchive";
			// alert("set action to"
			// + formIDAusgangsrechnungenKostenrechnungenExtended.action);
		}
		break;
	case 1:
		if (selectedIndexSearchType == 0) {// 1/0
			buchungsprotkolle.style.display = "block";
		} else {// 1/1
			buchungsprotkolleExtended.style.display = "block";
		}
		break;
	case 2:
		if (selectedIndexSearchType == 0) {// 2/0
			formIDAusgangsrechnungenKostenrechnungen.style.display = "block";
			formIDAusgangsrechnungenKostenrechnungen.action = "/archive/KostenrechnungenArchive";
			// alert("set action to"
			// + formIDAusgangsrechnungenKostenrechnungen.action);
		} else {// 2/1
			formIDAusgangsrechnungenKostenrechnungenExtended.style.display = "block";
			formIDAusgangsrechnungenKostenrechnungenExtended.action = "/archive/KostenrechnungenArchive";
			// alert("set action to"
			// + formIDAusgangsrechnungenKostenrechnungenExtended.action);
		}
		break;
	case 3:
		if (selectedIndexSearchType == 0) {// 3/0
			veranstalterrechnung.style.display = "block";
		} else {// 3/1
			veranstalterrechnungExtended.style.display = "block";
		}
		break;
	case 4:
		if (selectedIndexSearchType == 0) {// 3/0
			midocoBelege.style.display = "block";
		} else {// 3/1
			midocoBelegeExtended.style.display = "block";
		}
		break;
	default:
		break;
	}
}

function validateFirma(x) {

	var inputValue = document.getElementById("Firma_Mi").value;

	// var isNumber = typeof inputValue
	// TODO
	if (isNaN(inputValue)){
		alert("Bitte geben Sie eine Nummer ein.");
	} else if(inputValue.length != 4 && inputValue != ""){
		alert("Bitte 4 stellige Nummer eingeben");
		}
    // x.style.background = "yellow";
}