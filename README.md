# Welcome!
We're really excited about you joining our team! We designed this exercise to give you a taste of the challenges you may encounter in the role, and understand what it would be like to work closely together.


## About this exercise
You should expect to:

* Spend about 2 hours of your time. We focus on outcomes, not output, but want to set your expectations.
* 48-hour response time (like this challenge, if you submit on a weekend the timer starts the following Monday).

We're looking for the following skills:

* Software development fundamentals (we want to see an understanding of test automation, code reuse, and the fundamentals of whichever programming language you choose).
* System design (we're looking for logical method structure, domain modeling, and use of design patterns).
* Communication (your code should be easy to understand and reason about).


## Instructions

1. To get started follow the [task](#task) below.
2. Once you are ready to submit commit and push your changes with `git commit` and `git push`.
3. Return to the landing page you were originally provided (should be in your email), check that the latest commit we have from you is accurate, and then click the submit button. This will notify the engineers on our team that your work is ready for review.

## Task
One of the most important things Paytient does is give our users control over how they pay for their out-of-pocket medical expenses. Usually this happens through payroll deductions or automatic bank drafts, but imagine you are a new engineer on our Payments team and that after months of feature request upvotes, we are finally going to implement one-time payments.

Your objective is to write a new API endpoint, POST /one-time-payment, that accepts any payment amount greater than $0 and returns a user's updated balance and their next payment due date, which is always 15 days into the future. We are also experimenting with a new matching program where Paytient will apply a "match" payment as a percentage of the one-time payment to the remaining balance. Use the table below to determine what match to apply for a tiered system of one-time payments.

| One-time Payment Amount | Match |
|-------------------------|-------|
| 0 <= x < 10             | 1%    |
| 10 <= x < 50            | 3%    |
| X >= 50                 | 5%    |

Lastly, no one can have a payment due on a weekend (since that's a great way to ruin a weekend), so any payment due dates that fall on a Saturday or Sunday should be moved out to the next Monday.

For example, imagine someone with a $100 balance that makes a $10 one-time payment on March 14, 2022. They will have a new balance of $89.70 (their $10 payment plus 3% of $10, which is $0.30) and a next payment due date of March 29, 2022.

Similarly, someone with a $500 balance that makes a $75 one-time payment on April 8, 2022 will have a new balance of $421.25 (their $75 payment plus 5% of $75, which is $3.75) and a next payment due date of April 25, 2022.
