# Game: Bunch of feastures; each feature is a player

# 17 players for 34 different feature columns that represent 17 data sources (one from each hand)

import os
import pandas
import csv
import itertools


def normalize(df):
    result = df.copy()
    for feature_name in df.columns:
        max_value = df[feature_name].max()
        min_value = df[feature_name].min()
        result[feature_name] = (df[feature_name] - min_value) / (max_value - min_value)
    return result


# change working directory

os.chdir("/Users/hayden/workspace/GameTheoryforPipelining/10SignsBothHands")
col_names = ["Index", "player1", "player2", "player3", "player4", "player5", "player6", "player7", "player8", "player9",
             "player10", "player11", "player12", "player13", "player14", "player15", "player16", "player17",
             "player1.1", "player2.1", "player3.1", "player4.1", "player5.1", "player6.1", "player7.1", "player8.1",
             "player9.1", "player10.1", "player11.1", "player12.1", "player13.1", "player14.1", "player15.1",
             "player16.1", "player17.1"]

print(col_names)
coalitions_total_data = {}


for file in os.listdir("."):
    # print(file)
    file_frame = pandas.read_csv(file)
    file_frame.columns = col_names
    # file_frame = file_frame.drop['', 1]
    file_frame.drop('Index', axis=1, inplace=True)
    # print(file_frame)
    norm_file_frame = normalize(file_frame)

    player_vector = {
        1: [],
        2: [],
        3: [],
        4: []
    }

    # define player 1
    for i in range(1, 9):
        player_vector[1] = player_vector[1] + norm_file_frame['player' + str(i)].tolist() + norm_file_frame[
            'player' + str(i) + '.1'].tolist()

    for i in range(9, 12):
        player_vector[2] = player_vector[2] + norm_file_frame['player' + str(i)].tolist() + norm_file_frame[
            'player' + str(i) + '.1'].tolist()

    for i in range(12, 15):
        player_vector[3] = player_vector[3] + norm_file_frame['player' + str(i)].tolist() + norm_file_frame[
            'player' + str(i) + '.1'].tolist()

    for i in range(15, 18):
        player_vector[4] = player_vector[4] + norm_file_frame['player' + str(i)].tolist() + norm_file_frame[
            'player' + str(i) + '.1'].tolist()

    stuff = list(range(1, 5))
    for L in range(0, len(stuff) + 1):
        for subset in itertools.combinations(stuff, L):
            if (len(subset) > 0):
                #print("----------")
                temp = []
                for n in subset:
                    temp = temp + player_vector[n]
                if(str(subset) in coalitions_total_data):
                	coalitions_total_data[str(subset)] = []
                new_column_names = list(range(1, len(temp) + 1))
                new_column_names.append("class")
                
                if(not('class' in coalitions_total_data[str(subset)][0])):
                	coalitions_total_data[str(subset)].append[new_column_names]
                
                temp.append(file.split("_")[0])
                
                coalitions_total_data[str(subset)].append[temp]
                
                # print(type(subset))
                # print(new_column_names)
                # print(temp)
                # print(len(temp))
                # print( "coalitions\\coalitions"+str(subset)+".csv" )

                
               #  with open("../coalitions/coalitions" + str(subset) + ".csv", "w", newline="") as f:
#                     writer = csv.writer(f, delimiter=",")
#                     writer.writerows(t)

    # print("player"+str(i))
    # print(x)

    # player1 =

    # remove the first column

    # replace column names with meaningful names


    # Sprint(file_frame['X0.1'])

    # create player 1 csv
    # concat column 1 and 18








    # lumns

    # file_frame = pandas.read_csv()
#     break
    # define headers
    # read without headers into a matrix


for x in coalitions_total_data:
	print(x)
# with open("coalitions\\coalitions" + str(subset) + ".csv", "w", newline="") as f:
# 	writer = csv.writer(f, delimiter=",")
# 	writer.writerows(t)
    # define the coalitions
    # create a csv file in the format the naive bayes wants



# for a file in the folder
