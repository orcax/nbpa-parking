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
  * `location=[string]`
  * `startDate=[mm/dd/yyyy]`
  * `endDate=[mm/dd/yyyy]`
  * `weekdays=[list of integers among 1-7]`(separated by comma)
  * `startTime=[hh:mm]`
  * `endTime=[hh:mm]`
  * `columns=[list of strings among 'daily','monthly','total','rate']`(separated by comma)


