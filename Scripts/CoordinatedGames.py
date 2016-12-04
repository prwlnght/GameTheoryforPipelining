# Game: Bunch of feastures; each feature is a player

# 17 players for 34 different feature columns that represent 17 data sources (one from each hand)

import os
import pandas
import csv
import itertools

# Common Functions
def normalize(df):
    result = df.copy()
    for feature_name in df.columns:
        max_value = df[feature_name].max()
        min_value = df[feature_name].min()
        if(max_value != min_value):
            result[feature_name] = (df[feature_name] - min_value) / (max_value - min_value)
        else:
            result[feature_name] = 1
    return result

def define_player(player_vector, player_number):
    start = 0
    end = 0
    if player_number == 1:
        start = 1
        end = 9
    elif player_number == 2:
        start = 9
        end = 12
    elif player_number == 3:
        start = 12
        end = 15
    elif player_number == 4:
        start = 15
        end = 18

    for i in range(start, end):
        player_vector[player_number] = player_vector[player_number] + norm_file_frame['player' + str(i)].tolist() + norm_file_frame[
            'player' + str(i) + '.1'].tolist()
    return player_vector

# Working Directory
os.chdir("Path to data")

# Global Variables
col_names = ["Index", "player1", "player2", "player3", "player4", "player5", "player6", "player7", "player8", "player9",
             "player10", "player11", "player12", "player13", "player14", "player15", "player16", "player17",
             "player1.1", "player2.1", "player3.1", "player4.1", "player5.1", "player6.1", "player7.1", "player8.1",
             "player9.1", "player10.1", "player11.1", "player12.1", "player13.1", "player14.1", "player15.1",
             "player16.1", "player17.1"]

coalitions_total_data = {}

# For every file in the directory
for file in os.listdir("."):

    # Get file fileframe in the csv file
    file_frame = pandas.read_csv(file)

    # Add the column names to the file frame
    file_frame.columns = col_names

    file_frame.drop('Index', axis=1, inplace=True)

    # Normalize file frame
    norm_file_frame = normalize(file_frame)

    # Instantiate the player vector
    player_vector = {
        1: [],
        2: [],
        3: [],
        4: []
    }

    # Define the four players

    # Define player 1
    player_vector = define_player(player_vector, 1)

    # Define player 2
    player_vector = define_player(player_vector, 2)

    # Define player 3
    player_vector = define_player(player_vector, 3)

    # Define player 4
    player_vector = define_player(player_vector, 4)

    # for all playeri_id in player_vector
    for playeri_id in player_vector:
       
        # For each subset of player combinations combindations for player i
        for subset in itertools.combinations(list(range(1, 5)), playeri_id):

            # For all subsets with at least one player
            if (len(subset) > 0):

                # Data in the CSV
                data_for_subset = []
                for i in subset:
                    # Concatenate the player data from the subset
                    data_for_subset = data_for_subset + player_vector[i]
                
                # if the subset has not been added to coalitions_total_data then add it
                if(not(str(subset) in coalitions_total_data)):
                    coalitions_total_data[str(subset)] = []

                # Define the CSV headers
                column_headers = list(range(1, len(data_for_subset) + 1))
                column_headers.append("class")

                # # if not headers then initialize.
                if( len(coalitions_total_data[str(subset)]) == 0 ):
                    coalitions_total_data[str(subset)].append(column_headers)

                # Append the file data to the end of data_for_subset
                data_for_subset.append(file.split("_")[0])

                # append subset to totaldata 
                coalitions_total_data[str(subset)].append(data_for_subset)


# For Each coalitions in coalitions_total_data 
for coalitions in coalitions_total_data:
    # write to coalition file.
    with open("..\\coalitions\\coalitions" + coalitions + ".csv", "w", newline="") as f:
        writer = csv.writer(f, delimiter=",")
        writer.writerows(coalitions_total_data[coalitions])
