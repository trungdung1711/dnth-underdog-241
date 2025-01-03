
# Online Fashion Shopping

This is a github repository for the system called 'Online Fashion System' created by students at HCMUT (Ho Chi Minh University of Technology)


## Acknowledgements
 - [How to write a Good readme](https://bulldogjob.com/news/449-how-to-write-a-good-readme-for-your-github-project)
  - [Sequence Diagram Tutorial](https://online.visual-paradigm.com/diagrams/tutorials/sequence-diagram-tutorial/)
  - [Paypal sandbox dashboard](https://developer.paypal.com/dashboard/)
  - [The Unified Modeling Language](https://www.uml-diagrams.org/)
  - [Spring initializer](https://start.spring.io/)
  - [ChatGPT](https://chatgpt.com/)
## Authors

- [@trungdung1711](https://github.com/trungdung1711)


## Documentation

[Paypal integration Documentation](https://developer.paypal.com/studio/checkout/standard)


## Installation

Install online-fashion-shopping with mvn or ./mvnw

```bash
  cd ./back-end/
  mvn clean
  mvn install
```
    
## Environment Variables

To run this project, you will need to add the following environment variables: 
```bash
  export SPRING_MAIL_USERNAME=<mail>
  export SPRING_MAIL_PASSWORD=<password>
```
## Features

- Automatic report generation with pdf format
- Automatic email sending


## Run Locally

Clone the project

```bash
  git clone https://github.com/trungdung1711/dnth-underdog-241.git
```

Go to the project directory

```bash
  cd dnth-underdog-241/back-end
```

Install dependencies

```bash
  mvn clean install
```

Start the server

```bash
  mvn spring-boot:run
  or
  java -jar target/online-fashion-shopping-0.0.1-SNAPSHOT.jar
```


## Tech Stack

**Client:** React

**Server:** Spring boot, Flyway, Maven 


## Deployment

To deploy this project run

```bash
  mvn clean package
```


## Lessons Learned

What did you learn while building this project? What challenges did you face and how did you overcome them?

- Complex spring boot framework
- Difficult database management
- Security aspect
## Roadmap

- Additional browser support

- Add more integrations

- Add inventory management

- Add more sign up/sign in methods

- Add more payment methods

- Clean file upload management using Cloud service

- Update the database query process to boost performance
## Support

For support, email dung.lebk2210573@hcmut.edu.vn or trungdunglebui17112004@gmail.com


## Appendix

DATH in HCMUT