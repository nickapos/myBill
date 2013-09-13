This program is separated in three parts through the use of tabs

    * The first tab contains operations related to bill/receipts
    * The second tab contains operations related to the companies or organizations that have issued the above bills
    * The third tab is about reports and useful statistics

In more detail:

The first two tabs share the same philosophy. At the upper part of the tab we find a drop-down menu through which we can select an action to execute. The available actions on these tabs are the following three.

   1. Insert (Insert a new bill or company)
   2. Edit (Edit one already existing bill or company)
   3. Delete (Delete one already existing bill or company) 

Companies

The first thing we have to do in order to insert a new bill is to insert the company that has issued this bill. In order to insert the company we need two things. The company name and its tax service unique id number (in Greece named AFM). At the moment the maximum AFM length allowed is 9 alphanumeric characters and it must be unique. The length limit of the name is 254 characters and we do not have a unique constraint in this field. Both fields are obligatory. When we have completed the two fields, we hit the execution button (go).

After we have inserted the company, it receives a unique id number, by which we can manipulate this specific record. We can see this id by selecting the Reports tab and using the "Present Companies" report. The id appears at its first column. This id number is used when we want to edit the details of the company, or delete it. To do that we select the action, hit the Retrieve button, so that the fields are filled with the details of the company, change whatever we like and then hit the execute button (go).

Please bear in mind that when we delete a company, all relative bills with this company are deleted automatically.

Bills - Receipts

After inserting the company that has issued the bill or receipt on hand, we can now go on with the insertion of the bill itself. The procedure is almost identical with the one for the insertion of the company. We insert the date of issue of the bill, the date of payment (in year-month-date format e.g 2010-07-20), we select the company that has issued the bill from the dropdown list, the amount of the bill and hit the go button. The reason for the two dates is that there are cases when a bill is issued in one day and is not paid in the same date (e.g telephone bills, elecricity etc), when we have a receipt on hand (e.g from a supermarket) we enter the same date in both fields.

Likewise with the edit or delete action at the companies tab, we can find the bill id from the "Present Bills" report and change the details of the record or delete it altogether.

Income

At the current version (after 05 of August 2010), income tracking is also supported by the application, although not all related reports are complete yet.

The income tracking is based on the same idea as bills/receipts tracking: There is a dropdown list that contain all the companies on which you have received bills or receipts. Through this list you can track down the companies that pay you. Some times the company you pay may be the same with the company you spent money on. For example i work at a bank, from which i receive my salary and pay back an amount for a loan i have received.

Also the date of payment and the amount are required.

Reports

The third and last tab has to to exclusively with reports and statistics. Here we can find the basic 3 reports (companies, bills, income) and a number of other reports providing us with details as to how much money we have spent or receive, on which company, the total amount, reports per year, reports per month for a specific year etc. Some reports will generate a diagram of the data presented in textual form (expenses/month for a year and income/month for a year).

Other functions

The application except the mentioned functions supports some more through the use of special menus.These functions are:

   1. Shutting down the application and the database
   2. Deleting all the records of the database
   3. Exporting the database as csv in various ways
   4. Importing the database from a backup copy as a csv 
