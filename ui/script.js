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

function addDogBreed() {
    const addBreedName = document.getElementById('dog-breed-add').value.trim().toLowerCase();
    if (addBreedName === '') {
        alert('Please enter a breed name');
        return;
    }
    fetch(baseUrl + '/addDogBreed', postRequestJson({ breedName: addBreedName }))
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the response JSON
        })
        .then(data => {
            alert(data.message)
        })
        .catch(error => {
            alert('Error: ' + error)
        });
    loadHomePage();
}

function removeDogBreed() {
    const removeBreedName = document.getElementById('dog-breed-remove').value.trim().toLowerCase();
    if (removeBreedName === '') {
        alert('Please enter a breed name');
        return;
    }
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
        })
        .catch(error => {
            alert('Error: ' + error)
        });

    loadHomePage();
}

function addDogBreedType() {
    const addTypeBreedName = document.getElementById('dog-breed-add-type').value.trim().toLowerCase();
    const addTypeBreedTypeName = document.getElementById('dog-breedType-add').value.trim().toLowerCase();
    if (addTypeBreedTypeName === '' || addTypeBreedName === '') {
        alert('Please enter a breed name and a breed type name');
        return;
    }
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
        })
        .catch(error => {
            alert('Error: ' + error)
        });
    loadHomePage();
}

function removeDogBreedType() {
    const removeTypeBreedName = document.getElementById('dog-breed-remove-type').value.trim().toLowerCase();
    const removeTypeBreedTypeName = document.getElementById('dog-breedType-remove').value.trim().toLowerCase();
    if (removeTypeBreedName === '' || removeTypeBreedTypeName === '') {
        alert('Please enter a breed name and a breed type name');
        return;
    }
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
        })
        .catch(error => {
            alert('Error: ' + error)
        });
    loadHomePage();
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