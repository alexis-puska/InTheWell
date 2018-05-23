# InTheWell project
---------------------
[![travis build](https://travis-ci.org/alexis-puska/InTheWell.svg?branch=master)](https://travis-ci.org/alexis-puska/InTheWell) 
[![travis build](https://sonarcloud.io/api/project_badges/measure?project=InTheWell&metric=alert_status)](https://sonarcloud.io/dashboard?id=InTheWell) [![travis build](https://sonarcloud.io/api/project_badges/measure?project=InTheWell&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=InTheWell) [![travis build](https://sonarcloud.io/api/project_badges/measure?project=InTheWell&metric=security_rating)](https://sonarcloud.io/dashboard?id=InTheWell) [![travis build](https://sonarcloud.io/api/project_badges/measure?project=InTheWell&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=InTheWell)
[![travis build](https://sonarcloud.io/api/project_badges/measure?project=InTheWell&metric=ncloc)](https://sonarcloud.io/dashboard?id=InTheWell) [![travis build](https://sonarcloud.io/api/project_badges/measure?project=InTheWell&metric=bugs)](https://sonarcloud.io/dashboard?id=InTheWell) [![travis build](https://sonarcloud.io/api/project_badges/measure?project=InTheWell&metric=code_smells)](https://sonarcloud.io/dashboard?id=InTheWell) [![travis build](https://sonarcloud.io/api/project_badges/measure?project=InTheWell&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=InTheWell) [![travis build](https://sonarcloud.io/api/project_badges/measure?project=InTheWell&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=InTheWell)
---------------------
> In the well, game adaptation of hammerfest cavern with LibGdx java game library.
---------------------
**Compilation DESKTOP (windows/mac/unix)**

*build application for Desktop (Windows) :*
```sh
gradlew desktop:dist
```

*build application for Desktop (mac os X / ubuntu) :*
```sh
./gradlew desktop:dist
```

Executable jar is created in 
```sh
./desktop/build/dist
```

**Compilation RASPBERRY PI (RETROPIE)**

*connection SSH :*
```sh
reset ssh token : ssh-keygen -R "ip";
reset smb password : smbpasswd -a pi
```

*Installation on retropie :*

add this xml fragment to es_systems.cfg file in /etc/emulationstation/es_systems.cfg
```
  <system>
    <name>inTheWell</name>
    <fullname>inTheWell</fullname>
    <plateform>inTheWell</plateform>
    <path>/home/pi/RetroPie/roms/inTheWell</path>
    <command>sudo %ROM%</command>
    <extension>.sh</extension>
    <theme>hammerfest</theme>
  </system>
```


in a folder clone the project : 
```
cd /home/pi/RetroPie/roms
git clone https://github.com/alexis-puska/InTheWell.git
cd inthewell
chmod +x Update.sh
chmod +x Generate\ pi.sh
chmod +x Enter\ In\ The\ Well.sh
./Generate\ pi.sh
cd emulationStationTheme
sudo cp -avr hammerfest /etc/emulationstation/themes/carbon
sudo reboot
```

After this step In The Well is installed, compile, and integrated in emulation station on retropie distribution. If an update of code is made, you can just launch the UPDATE line in emulation station, COMPILE PI and launch the game with HAMMERFEST :) Enjoy !
