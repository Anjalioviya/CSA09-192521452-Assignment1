# Suggested test cases for Task 11 and Task 12

Run these against the implementation and record your actual results/screenshots.

| ID | Scenario | Expected result |
|---|---|---|
| T01 | Valid car enters an empty facility | Ticket created and one slot occupied |
| T02 | Vehicle exits with a 61-minute stay | Two billable hours |
| T03 | Zero-minute stay | Parking fee Rs 0 |
| T04 | Duplicate active registration enters | Rejected |
| T05 | Exit with unknown registration | Rejected |
| T06 | All slots occupied | Entry rejected with no-slot message |
| T07 | Valid student monthly pass | Parking fee waived |
| T08 | Valid staff monthly pass | Parking fee waived |
| T09 | Visitor without pass | Normal parking fee |
| T10 | Visitor with one-day pass | Parking fee waived |
| T11 | Electric car with 5 kWh | Add Rs 60 charging fee |
| T12 | E-bike | Flat Rs 20 fee |
| T13 | Delivery van billable hour beginning at 10:00 | Rs 60 for that hour |
| T14 | Delivery van billable hour beginning at 18:00 | Rs 30 for that hour |
| T15 | Floors with different capacities | All slots can be represented and counted |

Note: Some tests require adding test calls or a small test driver. Do not report a result as passed until you have executed it.
