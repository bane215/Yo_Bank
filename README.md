# Yo_Bank

Title: Banking System

Technical Details:
	This is a very simple core-Java console-based project which also uses MySQL database to maintain records.

Description:
 	This project is made from the perspectives of bank employees to uncomplicated maintenace of records of various accountholders. 
It provides for seven basic operations :
Opening an Account
Updating Customers' Information
Deposit
Withdrawal
View the list of all customers
View the list of all available accounts. 

There are five types of accounts namely Savings Account,Current Account,Fixed Account,Senior Citizen Account(Regular_Savings) and Young Stars Account.
			SAV        	CUR		FXD		SNR		YNG

OpeningBalance:  	2000		5000		10000		1500		500

MinAccBal:     		0		-10000		10000		0		500

Deposit:		>500		>500		5000*n		>500		>500

Withdrawal:		>100		>100		Full_Amt	>100		>100

Usage:
  Download the project and import into Eclipse. 
  Use 'yo_bank.sql' from Yo_Minor folder and import it into XAMPP. 
  Set JDBC connectivity.
  Start XAMPP (phpmyadmin).
  Run Yo_Bank in Eclipse.
