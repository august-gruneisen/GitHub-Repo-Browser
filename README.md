# GitHub-Repo-Browser
A wrapper on GitHub's API that allows for easy browsing of repos and their details

### Features:

1. Implement a network interface using Retrofit with functions for each call
	- Each call will need a separate data model for request and response
	- Data should flow through a repository to a view model
	- Store list of Repo objects in Room, accessible by its “id”

2. MainActivity should contain a fragment displaying a RecyclerView with each view holder bound to a repo
	- ViewHolder should be a reusable custom view that exposes the ability to set a click handler
	- Implement pagination if time permits

3. Custom repo view should include hidden fields for details (stars, watchers, issue count, forks count)
	- Click handler should simply toggle visibility for detail fields
	- Include a “Drill In” button that navigates to a new fragment displaying issues

4. Issues should have their own reusable custom view

### Notes:
- I just noticed that the /issues call defaults to open issues only. Use issues/?state=all to show all issues.
