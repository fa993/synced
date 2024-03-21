<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
[![Maven][maven-repo-shield]][maven-repo-url]
![Tests][ci-shield]
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">

<h3 align="center">synced</h3>

  <p align="center">
    Drop in persistent List and Map Implementation
    <br />
    <a href="https://github.com/fa993/synced"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/fa993/synced">View Demo</a>
    ·
    <a href="https://github.com/fa993/synced/issues">Report Bug</a>
    ·
    <a href="https://github.com/fa993/synced/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

Drop in Persisted implementations for the standard library List and Map interfaces. Add persistance to your application with minimal non-breaking code change. This library offers full control over where you want to persist your data, at the same time it offers some sane defaults to get started quickly.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

* [![Maven][Maven]][Maven-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

To include this library via maven

### Prerequisites

* Maven

### Installation

1. Modify your pom.xml file
   ```xml
   <repositories>
        <!--  Other repository tags  -->
        <repository>
            <id>fa993-repository</id>
            <url>https://repo.floricaninfosoft.com:2087/releases</url>
        </repository>
    </repositories>
   <!--  Other tags  -->
    <dependencies>
        <!--  Other dependency tags  -->
        <dependency>
            <groupId>com.fa3</groupId>
            <artifactId>synced</artifactId>
            <version>1.1.5</version>
        </dependency>
    </dependencies>
   
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Usage



<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [ ] SQLite support 
- [ ] Redis support

See the [open issues](https://github.com/fa993/synced/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Ameya Kore - kore.ameya@gmail.com

Project Link: [https://github.com/fa993/synced](https://github.com/fa993/synced)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* [Best-README-Template](https://github.com/othneildrew/Best-README-Template): For this amazing README template

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[ci-shield]: https://img.shields.io/github/actions/workflow/status/fa993/synced/maven.yml
[maven-repo-shield]: https://img.shields.io/badge/maven-1.1.x-green
[maven-repo-url]: https://repo.floricaninfosoft.com:2087/#/releases/com/fa3/synced
[contributors-shield]: https://img.shields.io/github/contributors/fa993/synced.svg
[contributors-url]: https://github.com/fa993/synced/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/fa993/synced.svg
[forks-url]: https://github.com/fa993/synced/network/members
[stars-shield]: https://img.shields.io/github/stars/fa993/synced.svg
[stars-url]: https://github.com/fa993/synced/stargazers
[issues-shield]: https://img.shields.io/github/issues/fa993/synced.svg
[issues-url]: https://github.com/fa993/synced/issues
[license-shield]: https://img.shields.io/github/license/fa993/synced.svg
[license-url]: https://github.com/fa993/synced/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/ameya-kore-925620239
[product-screenshot]: images/screenshot.png
[Maven]: https://img.shields.io/badge/maven-000000?logo=apachemaven
[Maven-url]: https://maven.apache.org/
