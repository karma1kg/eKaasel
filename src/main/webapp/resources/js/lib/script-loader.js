$(document).ready(function () {
    //Public JS region
    if (document.URL.search("marriageCertificate") > 1)
        scriptLoader("/resources/js/judiciary/public/marriageCertificate.js");
    if (document.URL.search("authenticationPersonalDetails") > 1)
        scriptLoader("/resources/js/judiciary/public/authenticationPersonalDetails.js");
    if (document.URL.search("affirmationMarriageCertificate") > 1)
        scriptLoader("/resources/js/judiciary/public/affirmationMarriageCertificate.js");
    if (document.URL.search("lostDocument") > 1)
        scriptLoader("/resources/js/judiciary/public/lostDocument.js");
    if (document.URL.search("singleMaritalStatus") > 1)
        scriptLoader("/resources/js/judiciary/public/singleMaritalStatus.js");
    if (document.URL.search("childTravelDocument") > 1)
        scriptLoader("/resources/js/judiciary/public/childTravelDocument.js");
    if (document.URL.search("attestationDocument") > 1)
        scriptLoader("/resources/js/judiciary/public/attestationDocument.js");
    if (document.URL.search("childAdoption") > 1)
        scriptLoader("/resources/js/judiciary/public/childAdoption.js");
    if (document.URL.search("organTransplant") > 1)
        scriptLoader("/resources/js/judiciary/public/organTransplant.js");
    if (document.URL.search("closingTransferAccounts") > 1)
        scriptLoader("/resources/js/judiciary/public/closingTransferAccounts.js");
    if (document.URL.search("attestAgreementWillsContract") > 1)
        scriptLoader("/resources/js/judiciary/public/attestAgreementWillsContract.js");
    //Notary JS region
    if (document.URL.search("notaryTaskList") > 1)
        scriptLoader("/resources/js/judiciary/notary/notaryTaskList.js");
    if (document.URL.search("approveMarriageCertificate") > 1)
        scriptLoader("/resources/js/judiciary/notary/approveMarriageCertificate.js");
    if (document.URL.search("approveAuthenticationPersonalDetails") > 1)
        scriptLoader("/resources/js/judiciary/notary/approveAuthenticationPersonalDetails.js");
    if (document.URL.search("approveAffirmationMarriageCertificate") > 1)
        scriptLoader("/resources/js/judiciary/notary/approveAffirmationMarriageCertificate.js");
    if (document.URL.search("approveLostDocument") > 1)
        scriptLoader("/resources/js/judiciary/notary/approveLostDocument.js");
    if (document.URL.search("approveSingleMaritalStatus") > 1)
        scriptLoader("/resources/js/judiciary/notary/approveSingleMaritalStatus.js");
    if (document.URL.search("approveChildTravelDocument") > 1)
        scriptLoader("/resources/js/judiciary/notary/approveChildTravelDocument.js");
    if (document.URL.search("approveAttestationDocument") > 1)
        scriptLoader("/resources/js/judiciary/notary/approveAttestationDocument.js");
    if (document.URL.search("approveChildAdoption") > 1)
        scriptLoader("/resources/js/judiciary/notary/approveChildAdoption.js");
    if (document.URL.search("approveOrganTransplant") > 1)
        scriptLoader("/resources/js/judiciary/notary/approveOrganTransplant.js");
    if (document.URL.search("approveClosingTransferAccounts") > 1)
        scriptLoader("/resources/js/judiciary/notary/approveClosingTransferAccounts.js");
    if (document.URL.search("approveAttestAgreementWillsContract") > 1)
        scriptLoader("/resources/js/judiciary/notary/approveAttestAgreementWillsContract.js");
    // Data Manager JS region
    if (document.URL.search("dataManagerTaskList") > 1)
        scriptLoader("/resources/js/judiciary/dataManager/dataManagerTaskList.js");
    if (document.URL.search("updateMarriageCertificate") > 1)
        scriptLoader("/resources/js/judiciary/dataManager/updateMarriageCertificate.js");
    if (document.URL.search("updateAuthenticationPersonalDetails") > 1)
        scriptLoader("/resources/js/judiciary/dataManager/updateAuthenticationPersonalDetails.js");
    if (document.URL.search("updateAffirmationMarriageCertificate") > 1)
        scriptLoader("/resources/js/judiciary/dataManager/updateAffirmationMarriageCertificate.js");
    if (document.URL.search("updateLostDocument") > 1)
        scriptLoader("/resources/js/judiciary/dataManager/updateLostDocument.js");
    if (document.URL.search("updateSingleMaritalStatus") > 1)
        scriptLoader("/resources/js/judiciary/dataManager/updateSingleMaritalStatus.js");
    if (document.URL.search("updateChildTravelDocument") > 1)
        scriptLoader("/resources/js/judiciary/dataManager/updateChildTravelDocument.js");
    if (document.URL.search("updateAttestationDocument") > 1)
        scriptLoader("/resources/js/judiciary/dataManager/updateAttestationDocument.js");
    if (document.URL.search("updateChildAdoption") > 1)
        scriptLoader("/resources/js/judiciary/dataManager/updateChildAdoption.js");
    if (document.URL.search("updateOrganTransplant") > 1)
        scriptLoader("/resources/js/judiciary/dataManager/updateOrganTransplant.js");
    if (document.URL.search("updateClosingTransferAccounts") > 1)
        scriptLoader("/resources/js/judiciary/dataManager/updateClosingTransferAccounts.js");
    if (document.URL.search("updateAttestAgreementWillsContract") > 1)
        scriptLoader("/resources/js/judiciary/dataManager/updateAttestAgreementWillsContract.js");

        //Offline application update for MC
    if (document.URL.search("offlineMarriageCertificate") > 1)
        scriptLoader("/resources/js/judiciary/dataManager/offlineMarriageCertificate.js");

    //Common JS region
    if (document.URL.search("trackApplication") > 1)
        scriptLoader("/resources/js/judiciary/trackApplication.js");
    if (document.URL.search("paymentDetail") > 1)
        scriptLoader("/resources/js/judiciary/paymentDetail.js");
    if (document.URL.search("reports") > 1)
        scriptLoader("/resources/js/judiciary/report/reports.js");
    if (document.URL.search("reportByCourt") > 1)
        scriptLoader("/resources/js/judiciary/report/reportByCourt.js");
    if (document.URL.search("reportByService") > 1)
        scriptLoader("/resources/js/judiciary/report/reportByService.js");
    if (document.URL.search("printCertificate") > 1)
        scriptLoader("/resources/js/judiciary/printCertificate.js");
    if (document.URL.search("login") > 1)
        scriptLoader("/resources/js/judiciary/home.js");
    if (document.URL.search("home") > 1)
        scriptLoader("/resources/js/judiciary/home.js");
});

var scriptLoader = function (url) {
    //url = window.location.protocol + '//' + window.location.host + '/judiciary' + url;
    $.ajax(
        {
            type: "GET",
            url: url,
            dataType: "script",
            cache: false
        });
};


