document.addEventListener("DOMContentLoaded", () => {
    const customerForm = document.getElementById("customerForm");
    const customerTableBody = document.querySelector("#customerTable tbody");
    const themeToggleIcon = document.getElementById("themeToggleIcon");
    const formTitle = document.getElementById("formTitle");
    const submitBtn = document.getElementById("submitBtn");
    const customerIdInput = document.getElementById("customerId");
    const toggleTableBtn = document.getElementById("toggleTableBtn");
    const apiBaseUrl = "http://localhost:8080/api/customers"; // Full API URL for backend

    // Theme Toggle Functionality
    themeToggleIcon.addEventListener("click", () => {
        document.body.classList.toggle("dark");
        if (document.body.classList.contains("dark")) {
            themeToggleIcon.classList.replace("fa-sun", "fa-moon");
        } else {
            themeToggleIcon.classList.replace("fa-moon", "fa-sun");
        }
    });

    // Function to fetch and display customers
    function fetchCustomers() {
        fetch(`${apiBaseUrl}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error("Failed to fetch customers");
                }
                return response.json();
            })
            .then(customers => {
                customerTableBody.innerHTML = ""; // Clear the table before adding new rows
                customers.forEach(customer => {
                    const row = `
                        <tr>
                            <td>${customer.id}</td>
                            <td>${customer.firstName}</td>
                            <td>${customer.lastName}</td>
                            <td>${customer.email}</td>
                            <td>${customer.mobileNumber}</td>
                            <td>${customer.age}</td>
                            <td>
                                <button class="edit-btn" data-id="${customer.id}">Edit</button>
                                <button class="delete-btn" data-id="${customer.id}">Delete</button>
                            </td>
                        </tr>`;
                    customerTableBody.insertAdjacentHTML('beforeend', row);
                });

                // Attach edit event listeners to each edit button
                document.querySelectorAll(".edit-btn").forEach(button => {
                    button.addEventListener("click", (e) => {
                        const customerId = e.target.getAttribute("data-id");
                        editCustomer(customerId);
                    });
                });

                // Attach delete event listeners to each delete button
                document.querySelectorAll(".delete-btn").forEach(button => {
                    button.addEventListener("click", (e) => {
                        const customerId = e.target.getAttribute("data-id");
                        deleteCustomer(customerId);
                    });
                });
            })
            .catch(error => {
                console.error("Error fetching customers:", error);
                alert("Error fetching customers");
            });
    }

    // Function to handle form submission (Add or Update)
    customerForm.addEventListener("submit", (event) => {
        event.preventDefault();

        const formData = new FormData(customerForm);
        const customerData = Object.fromEntries(formData.entries());

        // Convert age to a number
        customerData.age = parseInt(customerData.age);

        const customerId = customerIdInput.value;
        if (customerId) {
            // If customerId is present, perform update
            updateCustomer(customerId, customerData);
        } else {
            // Otherwise, insert new customer
            insertCustomer(customerData);
        }
    });

    // Function to insert a new customer
    function insertCustomer(customerData) {
        fetch(`${apiBaseUrl}/insert`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(customerData)
        })
        .then(response => {
            if (response.ok) {
                console.log("Customer inserted successfully");
                customerForm.reset(); // Clear the form after successful submission
                fetchCustomers();  // Refresh the customer list
            } else {
                console.error("Failed to insert customer:", response.status);
                alert("Failed to insert customer");
            }
        })
        .catch(error => {
            console.error("Error submitting customer data:", error);
            alert("Error submitting customer data");
        });
    }

    // Function to update an existing customer
    function updateCustomer(id, customerData) {
        fetch(`${apiBaseUrl}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(customerData)
        })
        .then(response => {
            if (response.ok) {
                console.log("Customer updated successfully");
                customerForm.reset(); // Clear the form after successful update
                customerIdInput.value = ""; // Clear the hidden customer ID field
                formTitle.textContent = "Add Customer"; // Reset form title
                submitBtn.textContent = "Submit"; // Reset button text
                fetchCustomers();  // Refresh the customer list
            } else {
                console.error("Failed to update customer:", response.status);
                alert("Failed to update customer");
            }
        })
        .catch(error => {
            console.error("Error updating customer:", error);
            alert("Error updating customer");
        });
    }

    // Function to delete a customer
    function deleteCustomer(id) {
        fetch(`${apiBaseUrl}/${id}`, {
            method: "DELETE"
        })
        .then(response => {
            if (response.ok) {
                console.log("Customer deleted successfully");
                fetchCustomers();  // Refresh the customer list
            } else {
                console.error("Failed to delete customer:", response.status);
                alert("Failed to delete customer");
            }
        })
        .catch(error => {
            console.error("Error deleting customer:", error);
            alert("Error deleting customer");
        });
    }

    // Function to pre-fill the form for editing a customer
    function editCustomer(id) {
        fetch(`${apiBaseUrl}/${id}`)
            .then(response => response.json())
            .then(customer => {
                // Fill form fields with customer data
                customerIdInput.value = customer.id;
                document.getElementById("firstName").value = customer.firstName;
                document.getElementById("lastName").value = customer.lastName;
                document.getElementById("email").value = customer.email;
                document.getElementById("mobileNumber").value = customer.mobileNumber;
                document.getElementById("age").value = customer.age;

                // Change form title and button text to indicate editing
                formTitle.textContent = "Edit Customer";
                submitBtn.textContent = "Update";
            })
            .catch(error => {
                console.error("Error fetching customer:", error);
                alert("Error fetching customer");
            });
    }

    // Function to show or hide the customer list
    toggleTableBtn.addEventListener("click", () => {
        const table = document.getElementById("customerTable");
        if (table.style.display === "none") {
            table.style.display = "table";
            toggleTableBtn.textContent = "Hide Customer List";
        } else {
            table.style.display = "none";
            toggleTableBtn.textContent = "Show Customer List";
        }
    });

    // Initial fetch to populate customer list
    fetchCustomers();
});
