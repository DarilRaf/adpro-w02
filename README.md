1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

- Removed some un-used variable

The SonarCloud reminded me on some code cleaning issues which are un-used variable that are still on my code.
I fixed it by simple removing it from my codebase

- Removed a commented out codes

Same as before, it also reminded me on some commented out codes that are in some files. I proceed to remove it from the codebase to make it look cleaner.


2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

I've added 3 workflows in total: CI, SonarCloud, and Koyeb.
From those 3 workflows, I think it has covered most of the CI/CD principle, such as the continuous
integration that I had on ci.yml makes sure that the code has run through some re-building so it makes sure nothing
is getting out of the way. On the other hand Koyeb workflow is used so it could automatically deploy my application
to the koyeb service, so I won't have to do it manually.

koyeb web link: https://implicit-merissa-darilraf.koyeb.app/
