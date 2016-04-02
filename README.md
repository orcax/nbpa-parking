# NBPA Parking Statistical Tool
## Git Usage
```shell
# Fetch codes from remote repository
git pull
# Submit code to local repository
git add -A
git commit "some update comment here"
# Sync to remote repository
git push -u origin master
```
## API Description
* **Get occupancy list by conditions.**

  **URL:** /occupancy

  **Method:** `GET`
  
  **Data Params:** 
  * `location=[list of strings]`
  * `start_date=[mm/dd/yyyy]`
  * `end_date=[mm/dd/yyyy]`
  * `weekdays=[list of integers among 1-7]`(separated by comma)
  * `start_time=[hh:mm]`
  * `end_time=[hh:mm]`
  * `columns=[list of strings among 'daily','monthly','total','rate']`(separated by comma)


