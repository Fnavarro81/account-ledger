````mermaid
flowchart TD
A1[Main] --> B[runHomeScreen]
B --> C1[D.Add Deposit/addDeposit]
B --> C2[P.Make Payment/makePayment]
B --> C3[L.Ledger/runLedgerScreen]
B --> C4[X.Exit]
C3 --> D1[A.All/seeAll]
C3 --> D2[D.Deposits/addDeposits]
C3 --> D3[P.Payments/makePayments]
C3 --> D4[R.Reports/runReportsScreen]
C3 --> D5[H.Home/returnHome]
D4 --> E1[1.Month To Date/viewMonthToDate]
D4 --> E2[2.Previous Month/viewPreviousMonth]
D4 --> E3[3.Year to Date/viewYearToDate]
D4 --> E4[4.Previous Year/viewPreviousYear]
D4 --> E5[5.Search by Vendor/searchByVendor]
D4 --> E6[6.Back/backToLedger]
````
