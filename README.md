# Docker-based Selenium Grid Demo

This project is a simple demo of setting up a Selenium Grid (Hub and two nodes) to be used by Selenium tests within the application.  The app itself contains only test classes, which are executed during a Maven build.


## Running the Build/Tests

Rather than running Maven directly, we're going to spin up the Grid and execute Maven within a Docker container.

```
docker-compose up --force-recreate --abort-on-container-exit
```

- Starts the Grid (hub and two nodes)
- Runs a [Maven Container](https://hub.docker.com/_/maven/), mounting the current project directory as the working directory for the container
- The Maven build then runs in the container and utilizes the Selenium grid using the Docker overlay network

### What's up with the arguments?

- **--force-recreate** - gets around a bug documented at https://github.com/SeleniumHQ/docker-selenium/issues/91
- **--abort-on-container-exit** - simply tears down the stack once the maven container finishes.  Otherwise, once Maven is done, the Selenium Grid will keep running forever

## Why use Docker?

- Makes starting up and tearing down of the Grid super easy
- Easily reproducible. By running Docker Compose, the app will run the same, no matter what is already installed on the machine
- Linked containers create predictable hostnames. When running Arquillian tests, no service discovery/hard-coding has to be made to determine the IP address/host of the hub. It's simply the linked container at host ```hub```.

## The tests

There are two test cases in the project.  Both tests are running tests against my blog, found at https://blog.mikesir87.io/

- ```io.mikesir87.demo.selenium.BlogTest``` - a test case using the Selenium WebDriver API directly. Looks very similar to the direct output from the Selenium IDE in Firefox
- ```io.mikesir87.demo.arquillian.BlogTest``` - a similar test case, but utilizing Arquillian Drone and Graphene in standalone mode. Hopefully you can see how much easier it is to read the test case and understand what the test is actually testing. **Page fragments rock!!!**

