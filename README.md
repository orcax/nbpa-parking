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
  
  **URL Params:** `id=[integer]`

  **Data Params:**  None

  **Success Response:**
  * **Code:** 200 <br />
    **Content:** `{ id : 12, name : "Michael Bloom" }`


