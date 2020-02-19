# GitHub-Repo-Browser
A wrapper on GitHub's API that allows for easy browsing of repos and their details

### Features:

1. Implements a network interface using Retrofit
	- Each call uses a data model for request and response
	- Data flows through a view model

2. MainActivity code is empty. Fragments display results from the API with data binding
	- Utilizes NavHostFragment to navigate between fragments
	- Most logic is within ViewModel

3. Repo card view includes hidden fields for details (stars, watchers, issue count, forks count)
	- Click handler toggles visibility for details
	- Long click “Drill In” navigates to a new fragment displaying issues

4. Issues are displayed using Truffle Shuffle library :)

### Limitations:
- Does not implement pagination, therefore only first 30 results are displayed
- Does not persist data, therefore requires stable connection
- Limited error handling for network requests. If successful, displays results -> else, Toast error
- Does not yet provide an easy way for the user to view other repositories
