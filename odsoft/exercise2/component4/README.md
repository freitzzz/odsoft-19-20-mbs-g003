# Class Assignment 2 Component 4

The goal of this class assignment is to design and create a Jenkins Pipeline that performs a set of tasks declared in a Scripted Jenkinsfile, as a parallel build. If any of the tasks fail, the pipeline build should immediately fail.

The tasks to be performed are the following:

- `Repository Checkout`: Checkout the GIT repository
- `War file`: Build the .war file and publish it on Jenkins
- `Javadoc`: Build the Javadoc and publish it on Jenkins
- `Unit Tests`: Execute the Unit Tests, generate a Unit Tests Report, generate a Unit Tests Coverage Report and publish both reports on Jenkins
- `Integration Tests`: Execute the Integration Tests, generate an Integration Tests Report, generate an Integration Tests Coverage Report and publish both reports on Jenkins
- `Mutation Tests`: Execute the Mutation Tests, generate a Mutation Coverage Report and publish it on Jenkins
- `System Test`: Deploy the application (.warfile) to a pre-configured Tomcat Serverinstance. Perform an automatic smoke test. This smoke test can be as simple as using curl to check if the base url of the application is responsive after deployment in the Tomcat Server, ensuring that the application is properly deployed to the Staging Environment
- `UI Acceptance Manual Tests`: A user should be notified by email of the successful execution of all the previous tests and be asked to perform a manual test. In order to cancel the progression or proceed, a UI Acceptance Manual Test must take place. The pipeline should wait for a user manual confirmation on Jenkins
- `Continuous Integration Feedback`: Push a tag to the repository with the Jenkins build number and status (e.g. Build#32-Passed or Build#32-Failed)


## Pipeline Design

To conceive the creation of the pipeline, the following activity diagram proposed the execution flow of the pipeline:

![PIPELINE_ACTIVITY_DIAGRAM](jenkins/pipeline_design/PIPELINE_DESIGN_ACTIVITY_DIAGRAM.png)


## Pipeline Implementation

In order to implement the pipeline, a Scripted Jenkinsfile was created which exports the logic for the pipeline execution. This file is organized in two sections:

- Functions used by the pipeline declaration
- Pipeline node declaration

In order to execute the pipeline, Jenkins must be prepared to use the following plugins:

|Plugin|Description|
|------|-----------|
|[Publish HTML](https://wiki.jenkins.io/display/JENKINS/HTML+Publisher+Plugin)|Publishes in Jenkins an item that displays an HTML document|
|[Deploy Plugin](https://wiki.jenkins.io/display/JENKINS/Deploy+Plugin)|Allows the deployment of a WAR/EAR file to a running remote application server. Used to deploy WAR file on Tomcat server|
|[Email-ext plugin](https://wiki.jenkins.io/display/JENKINS/Email-ext+plugin)|Allows the send of an email|

### Pipeline Declared Functions

The functions declared in the first section provide functionalities such as execution of commands in the system defined interface or information retrieval. The following table describes the declared functions:

|Function|Description|
|--------|-----------|
|`run_command(command)`|Runs a command on the system defined interface (if in Unix system calls sh step, else bat step for Windows)|
|`run_gradlew(gradlew_commands)`|Runs gradle wrapper located in the folder of the current running execution environment|
|`get_bitbucket_username()`|Allows the retrieval of the bitbucket username defined in bitbucketCredentials authentication keys|
|`get_bitbucket_password()`|Allows the retrieval of the bitbucket username defined in bitbucketCredentials authentication keys|
|`resolve_path(path)`|Resolves a given path by replacing either / with \\ in Windows, or replacing \\ with / in Unix systems|
|`get_http_request(uri)`|Performs a GET HTTP request. In Unix system uses curl, Invoke-RestMethod on Windows using powershell|

### Pipeline node

The pipeline node starts by declaring a set of variables used in the pipeline stages. This is done in order to remove duplicated code, as each pipeline stage requires the value given by these variables more than one time. The following table describes the declared variables:

|Variable|Description|
|--------|-----------|
|`passed_build_tag_name`|Marks the name of the tag that will be pushed to repository if the pipeline build succeeds|
|`failed_build_tag_name`|Marks the name of the tag that will be pushed to repository if the pipeline build fails|
|`tomcat_server_url`|Indicates the URL in which the Tomcat Server is running|
|`bitbucket_username`|Indicates the username to be used in bitbucket repositories|
|`bitbucket_password`|Indicates the password to be used in bitbucket repositories|
|`cms_path`|Indicates the relative path starting from the pipeline workspace to cms folder|

Next a set of stages are declared in which each of these perfom a set of actions relative to the stage. The following tables describes the declared stages:

|Stage|Description|
|-----|-----------|
|`Checkout`|Performs a Git checkout of the repository to be used in the pipeline and configures Git local account name and email. This last step is required as the pipeline needs to create and push tags to the repository. If this stage fails, the pipeline fails and no tag is pushed to the repository|
|`Pre Build Tests`|Performs unit, integration, mutation tests and their reports generation of the cms project. These actions are performed in parallel and gradle wrapper is invoked to execute these. Although the pipeline is designed to generate tests reports in artifacts build stage, the generation of these is perfomed in this stage as the tasks that allow the test of the cms project are pre-configured to generate the reports. If this stage fails, the pipeline fails and a tag is pushed to repository informing that the pipeline has failed in pre build tests stage|
|`Artifacts Build`|Builds the WAR and Javadoc artifacts of the cms project. These build is perfomed in parallel as war and javadoc do not depend on each other. Gradle wrapper is invoked to perfom the build of these artifacts. If this stage fails, the pipeline fails and a tag is pushed to repository informing that the pipeline has failed in artifacts build stage|
|`Artifacts Publish`|Publishes built artifacts in Jenkins. Test Reports and Javadoc publish is performed using `publishHTML` step and WAR using `archiveArtifacts` step. Archive Artifacts step could also be used to publish the reports and javadoc, but it was preferred to use Publish HTML plugin as the output in the Jenkins page is prettier and more usable. The publish of these artifacts is performed in parallel as there is no requirement in the order of the publish. If this stage fails, the pipeline fails and a tag is pushed to repository informing that the pipeline has failed in artifacts publish stage|
|`Deploy and Post Build Tests`|Deploys the built WAR artifact in a Tomcat server, performs a smoke test and informs the user that manual tests are required to continue the pipeline build via an email notification. The perfomed smoke test is a simple test that only checks if the server is available after deploy. For user manual confirmation to signal the pipeline if its build can continue or not, the `input` step was used, which pauses the pipeline and displays a confirmation box in the Jenkins UI. If the user accepts the continuation of the pipeline build, a tag is created and pushed in the repository, informing that the pipeline build was successful. If this stage fails, the pipeline fails and a tag is pushed to repository informing that the pipeline has failed in deploy and post builds stage|


## Pipeline Performance

The pipeline was tested and run using `docker-compose` that launches a container for the most recent Jenkins version (`jenkins/jenkins:lts`) and a container for Tomcat Server 9.0.27 (`tomcat:9.0.27-jdk8-openjdk`).
The machine used has the following specs:

```
System:    Host: freitas-X550CC Kernel: 4.15.0-46-generic x86_64
           bits: 64 gcc: 7.3.0
           Desktop: LXDE (Openbox 3.6.1) Distro: Ubuntu 18.04.2 LTS
Machine:   Device: laptop System: ASUSTeK product: X550CC v: 1.0 serial: N/A
           Mobo: ASUSTeK model: X550CC v: 1.0 serial: N/A
           UEFI: American Megatrends v: X550CC.217 date: 10/16/2013
Battery    BAT0: charge: 22.4 Wh 99.8% condition: 22.4/44.6 Wh (50%)
           model: ASUSTeK X550A30 status: Full
CPU:       Dual core Intel Core i7-3537U (-MT-MCP-) 
           arch: Ivy Bridge rev.9 cache: 4096 KB
           flags: (lm nx sse sse2 sse3 sse4_1 sse4_2 ssse3 vmx) bmips: 9976
           clock speeds: max: 3100 MHz 1: 1078 MHz 2: 1016 MHz 3: 1031 MHz
           4: 984 MHz
Graphics:  Card-1: Intel 3rd Gen Core processor Graphics Controller
           bus-ID: 00:02.0
           Card-2: NVIDIA GK208M [GeForce GT 720M] bus-ID: 01:00.0
           Display Server: x11 (X.Org 1.19.6 )
           drivers: modesetting,nouveau (unloaded: fbdev,vesa)
           Resolution: 1366x768@60.00hz, 1920x1080@60.00hz
           OpenGL: renderer: Mesa DRI Intel Ivybridge Mobile
           version: 4.2 Mesa 18.2.8 Direct Render: Yes
Audio:     Card Intel 7 Series/C216 Family High Def. Audio Controller
           driver: snd_hda_intel bus-ID: 00:1b.0
           Sound: Advanced Linux Sound Architecture v: k4.15.0-46-generic
Network:   Card-1: Qualcomm Atheros AR9485 Wireless Network Adapter
           driver: ath9k bus-ID: 03:00.0
           IF: wlp3s0 state: up mac: <filter>
           Card-2: Realtek RTL8111/8168/8411 PCIE Gigabit Ethernet Controller
           driver: r8169 v: 2.3LK-NAPI port: d000 bus-ID: 04:00.2
           IF: enp4s0f2 state: down mac: <filter>
Drives:    HDD Total Size: 500.1GB (14.2% used)
           ID-1: /dev/sda model: Samsung_SSD_860 size: 500.1GB temp: 0C
Partition: ID-1: / size: 120G used: 67G (59%) fs: ext4 dev: /dev/sda5
RAID:      No RAID devices: /proc/mdstat, md_mod kernel module present
Sensors:   System Temperatures: cpu: 62.0C mobo: N/A gpu: 54.0
           Fan Speeds (in rpm): cpu: 3000
Info:      Processes: 210 Uptime: 2 days Memory: 6462.2/7862.8MB
           Init: systemd runlevel: 5 Gcc sys: 7.3.0
           Client: Shell (bash 4.4.191) inxi: 2.3.56
```

On a clean workspace, the pipeline shows the following performance results on each stage:

|Stage|Time|Parallel Stage|Explanation|
|-----|----|--------------|-----------|
|Checkout|`3 seconds`|No|Takes about 1 to 5 seconds to complete this stage as the most time-cost operation is the Git repository checkout. Could take more time to complete if the repository space scales higher and the network latency is higher|
|Pre Build Tests|`2 minutes and 43 seconds`|Yes|Integration tests take about 2 minutes and 15 seconds to run. This is due to the requirement of launching a browser instance to perfom the tests.|
|Artifacts Build|`1 minute and 41 seconds`|Yes|WAR build takes about 1 minute and 40 seconds to complete as permutations are being computed for four different browsers (Chrome, Firefox, Opera and Safari). Removing supported browsers speeds up build|
|Artifacts Publish|`925 milliseconds`|Yes|Can be slower if files space to publish on Jenkins is higher|
|Deploy and Post Build Tests|`7 seconds`|No|Deployment and tag push takes about 2 seconds. Can be slower if network latency is higher|

Sequential builds show the following performance results:

|Stage|Time|Parallel Stage|Explanation|
|-----|----|--------------|-----------|
|Checkout|`1 second`|No|If no changes were detected in the repository, checkout is faster as it uses local repository existent on the workspace|
|Pre Build Tests|`28 seconds`|Yes|If no changes were dected in the source or test code, unit, mutation and integration do not require to be executed again|
|Artifacts Build|`3 seconds`|Yes|If no changes in the source code are dected, WAR is not required to be built again|
|Artifacts Publish|`702 milliseconds`|Yes|Can be slower if files space to publish on Jenkins is higher|
|Deploy and Post Build Tests|`8 seconds`|No|Deployment and tag push takes about 2 seconds. Can be slower if network latency is higher|