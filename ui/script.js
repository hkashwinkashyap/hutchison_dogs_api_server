const baseUrl = 'http://localhost:8080/dogsApi';

function postRequestJson(requestData) {
    return {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestData), // Convert the data to JSON string
    };
}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function setColCount() {
    // Set colCount based on the window width
    if (window.innerWidth >= 990) { // Desktop size or larger
        return 4;
    } else if (window.innerWidth >= 720 && window.innerWidth < 990) {
        return 3;
    }
    else {
        return 2;
    }
}

function addHandle() {
    const addBreedName = document.getElementById('dog-breed-add').value.trim().toLowerCase().replace(/\s+/g, " ");
    const addBreedTypeName = document.getElementById('dog-breedType-add').value.trim().toLowerCase().replace(/\s+/g, " ");

    if (addBreedName === '') {
        alert('Please enter a Breed name');
        return;
    }

    if (addBreedName !== '' && addBreedTypeName === '') {
        addDogBreed(addBreedName);
    }

    if (addBreedName !== '' && addBreedTypeName !== '') {
        addDogBreedType(addBreedName, addBreedTypeName);
    }
}

function addDogBreed(addBreedName) {

    fetch(baseUrl + '/addDogBreed', postRequestJson({ breedName: addBreedName }))
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the response JSON
        })
        .then(data => {
            alert(data.message)
            loadHomePage();
        })
        .catch(error => {
            alert('Error: ' + error)
        });
}

function addDogBreedType(addTypeBreedName, addTypeBreedTypeName) {
    fetch(baseUrl + '/addDogBreedType', postRequestJson({
        breedName: addTypeBreedName,
        breedTypeName: addTypeBreedTypeName
    }))
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the response JSON
        })
        .then(data => {
            alert(data.message)
            loadHomePage();
        })
        .catch(error => {
            alert('Error: ' + error)
        });
}

function removeHandle() {
    const removeBreedName = document.getElementById('dog-breed-remove').value.trim().toLowerCase().replace(/\s+/g, " ");
    const removeBreedTypeName = document.getElementById('dog-breedType-remove').value.trim().toLowerCase().replace(/\s+/g, " ");

    if (removeBreedName === '') {
        alert('Please enter a Breed name');
        return;
    }

    if (removeBreedName !== '' && removeBreedTypeName === '') {
        removeDogBreed(removeBreedName);
    }

    if (removeBreedName !== '' && removeBreedTypeName !== '') {
        removeDogBreedType(removeBreedName, removeBreedTypeName);
    }
}

function removeDogBreed(removeBreedName) {
    message = 'Are you sure you want to remove "' + removeBreedName + '" Breed?';
    if (!confirm(message)) {
        return;
    }
    fetch(baseUrl + '/deleteDogBreed', postRequestJson({ breedName: removeBreedName }))
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the response JSON
        })
        .then(data => {
            alert(data.message)
            loadHomePage();
        })
        .catch(error => {
            alert('Error: ' + error)
        });
}

function removeDogBreedType(removeTypeBreedName, removeTypeBreedTypeName) {
    message = 'Are you sure you want to remove "' + removeTypeBreedTypeName + '" from "' + removeTypeBreedName + '" Breed?';
    if (!confirm(message)) {
        return;
    }
    fetch(baseUrl + '/deleteDogBreedType', postRequestJson({
        breedName: removeTypeBreedName,
        breedTypeName: removeTypeBreedTypeName
    }))
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the response JSON
        })
        .then(data => {
            alert(data.message)
            loadHomePage();
        })
        .catch(error => {
            alert('Error: ' + error)
        });
}

function updateHandle() {
    const updateBreedName = document.getElementById('dog-breed-update').value.trim().toLowerCase().replace(/\s+/g, " ");
    const newBreedName = document.getElementById('dog-breed-update-new').value.trim().toLowerCase().replace(/\s+/g, " ");

    const updateBreedTypeName = document.getElementById('dog-breedType-update').value.trim().toLowerCase().replace(/\s+/g, " ");
    const newBreedTypeName = document.getElementById('dog-breedType-update-new').value.trim().toLowerCase().replace(/\s+/g, " ");

    if (updateBreedName === '') {
        alert('Please enter a Breed name');
        return;
    }

    else if (updateBreedName !== '' && newBreedName !== '' && updateBreedTypeName === '' && newBreedTypeName === '') {
        updateBreedNameFunc(updateBreedName, newBreedName);
    }

    else if (updateBreedName !== '' && newBreedName === '' && updateBreedTypeName !== '' && newBreedTypeName !== '') {
        updateBreedTypeNameFunc(updateBreedName, updateBreedTypeName, newBreedTypeName);
    }

    else if (updateBreedName !== '' && newBreedName !== '' && updateBreedTypeName !== '' && newBreedTypeName !== '') {
        alert('All fields are filled, please enter a Breed name to update or a Breed Type name to update not both');
        return;
    }

    else {
        alert('Invalid input, please enter a Breed name to update or a Breed Type name to update.');
        return;
    }
        
}

function updateBreedNameFunc(updateBreedName, newBreedName) {
    message = 'Are you sure you want to update "' + updateBreedName + '" Breed to "' + newBreedName + '"?';
    if (!confirm(message)) {
        return;
    }
    fetch(baseUrl + '/updateDogBreedName', postRequestJson({ 
        breedName: updateBreedName, 
        newBreedName: newBreedName 
    }))
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the response JSON
        })
        .then(data => {
            alert(data.message)
            loadHomePage();
        })
        .catch(error => {
            alert('Error: ' + error)
        });
}

function updateBreedTypeNameFunc(updateBreedName, updateBreedTypeName, newBreedTypeName) {
    message = 'Are you sure you want to update "' + updateBreedTypeName + '" to "' + newBreedTypeName + '" from "' + updateBreedName + '" Breed?';
    if (!confirm(message)) {
        return;
    }
    fetch(baseUrl + '/updateDogBreedTypeName', postRequestJson({
        breedName: updateBreedName,
        breedTypeName: updateBreedTypeName,
        newBreedTypeName: newBreedTypeName
    }))
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the response JSON
        })
        .then(data => {
            alert(data.message)
            loadHomePage();
        })
        .catch(error => {
            alert('Error: ' + error)
        });
}

function loadHomePage() {
    const dogList = document.getElementById('dog-list');

    dogList.innerHTML = '';


    fetch(baseUrl + '/getAllDogs')
        .then(response => response.json())
        .then(jsonData => {
            const lowercaseData = {};
            for (const breed in jsonData.data) {
                lowercaseData[breed.toLowerCase()] = jsonData.data[breed].map(subBreed => subBreed.toLowerCase());
            }
            const dogData = Object.keys(lowercaseData).sort();
            const dogList = document.getElementById('dog-list');
            let row = document.createElement("div");
            row.classList.add("row");
            dogList.appendChild(row);

            let colCount = 0; // Keep track of the number of columns

            for (const breed of dogData) {
                if (colCount === setColCount()) {
                    row = document.createElement("div");
                    row.classList.add("row");
                    dogList.appendChild(row);
                    colCount = 0;
                }

                const breedCard = document.createElement("div");
                breedCard.classList.add("card");
                breedCard.classList.add("col");
                row.appendChild(breedCard);

                const breedCardContent = document.createElement("div");
                breedCardContent.classList.add("card-body");
                breedCard.appendChild(breedCardContent);

                const breedTitle = document.createElement("h5");
                breedTitle.classList.add("card-title");
                breedTitle.textContent = capitalizeFirstLetter(breed); // Capitalize the breed name
                breedCardContent.appendChild(breedTitle);

                const breedTypeName = document.createElement("div");
                breedTypeName.classList.add("list-group");

                const breedTypeNames = lowercaseData[breed];
                for (const subBreed of breedTypeNames) {
                    const breedType = document.createElement("li");
                    breedType.classList.add("list-group-item");
                    breedType.textContent = capitalizeFirstLetter(subBreed); // Capitalize the breedType name
                    breedTypeName.appendChild(breedType);
                }

                breedCardContent.appendChild(breedTypeName);

                colCount++;
            }
        })
        .catch(error => {
            console.error('Error fetching data: ' + error);
        });
}

window.onload = loadHomePage;
window.onresize = loadHomePage;