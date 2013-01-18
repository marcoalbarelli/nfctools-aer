nfctools-aer
============

Android External Reader, Make your Android device NFC capable 

This projects aims to make NFC incapable devices NFC capable through the use of an external ACR122U usb reader
Android devices need to be able to act as USB HOST so you need API level 12 or higher

Current Status
--------------
Right now the project manages the connection to the Reader, using an intent-filter
It's capable of reading the second page of data from a MifareUltralight card
Integration with NFC tools will make it able to read various tags

ToDo
----
Implement reading of generic NFC tags
Implement Writing/locking on tags
Implement P2P protocol

License
-------
This code is mostly based on the ACR122U demo which you can easily obtain at this link
http://www.acs.com.hk/index.php?pid=product&id=ACR122U
Refer to them for license terms (I didn't find any)

Makes use of the acssmc library which comes in the same package (no-source code for that)

Makes use of the nfctools library //need to link to the github project
